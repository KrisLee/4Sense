package esa.gmes.activity.base;

import esa.gmes.logic.LocationService;
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Abstract Activity which inherits the properties of {@link BaseActivity},
 * registers a listener when the activity is instantiated and stops listening to
 * updates on destroy. Forces child classes to override the updateLocation
 * method, which is called when the location is changed.
 *
 * @author Jo‹o Xavier <joao.xavier@novabase.pt>
 */
public abstract class LocationAwareActivity extends Activity {

    /**
     * Difference of time, in milliseconds, from which a location can be
     * considered significantly older or newer.
     */
    private static final int DEFAULT_DELTA_MILLIS = 30000;
    /**
     * Difference of accuracy from which a location can be considered
     * significantly less accurate than another.
     */
    private static final int SIGNIFICANT_ACCURACY_DELTA = 200;

    /**
     * The {@link LocationListener}, listening to coarse provider updates,
     * attached to this activity.
     */
    private LocationListener mLocationListener;
    /**
     * The {@link LocationListener}, listening to gps provider updates, attached
     * to this activity.
     */
    private LocationListener mGpsListener;
    /**
     * The last best location.
     */
    private Location mLocation;

    /**
     * Initialize the {@link LocationListener} and
     * {@link pt.nb.acp.map.MapViewController} objects with the appropriate
     * context, starts listening to location updates and promptly tries to
     * acquire location from cache.
     */
    protected final void initializeLocationAwareness() {
        mLocation = null;

        // define a listener for gps updates
        mGpsListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                chooseLocation(location);
            }

            @Override
            public void onProviderEnabled(final String provider) {
            }

            @Override
            public void onProviderDisabled(final String provider) {
            }

            @Override
            public void onStatusChanged(final String provider,
                    final int status, final Bundle extras) {
            }
        };

        // define a listener for coarse location updates
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                chooseLocation(location);
            }

            @Override
            public void onStatusChanged(final String provider,
                    final int status, final Bundle extras) {
            }

            @Override
            public void onProviderEnabled(final String provider) {
                LocationService.removeUpdates(mLocationListener);
                LocationService.updateProvider();
                LocationService
                    .requestLocationUpdates(mLocationListener, false);
            }

            @Override
            public void onProviderDisabled(final String provider) {
                LocationService.removeUpdates(mLocationListener);
                LocationService.updateProvider();
                LocationService
                    .requestLocationUpdates(mLocationListener, false);
            }
        };

        // initialize LocationService
        LocationService.setContext(this);
        // register the listeners with the LocationService to receive location
        // updates, non-verbose
        requestLocationUpdates();
        // get a fast fix - cached version
        updateLocation(LocationService.getLastKnownLocation());
    }

    /**
     * Starts listening to location updates.
     */
    protected final void requestLocationUpdates() {
        LocationService.requestLocationUpdates(mLocationListener, false);
        LocationService.requestGpsUpdates(mGpsListener);
    }

    /**
     * Stops listening to location updates.
     */
    protected final void removeLocationUpdates() {
        LocationService.removeUpdates(mGpsListener);
        LocationService.removeUpdates(mLocationListener);
    }

    /**
     * Defines the best location based on the old location and the new location
     * and calls the abstract method updateLocation() with the best location.
     *
     * @param newLocation
     *            a new {@link Location} object, issued by a
     *            {@link LocationListener}.
     */
    private void chooseLocation(final Location newLocation) {
        if (isBetterLocation(newLocation)) {
            mLocation = newLocation;
        }

        updateLocation(mLocation);
    }

    /**
     * Whether the passed location is better than the old location.
     *
     * @param newLocation
     *            the new location.
     * @return <code>true</code> if the new location is better than the previous
     *         one, <code>false</code> otherwise.
     */
    private boolean isBetterLocation(final Location newLocation) {
        if (mLocation == null) {
            // a new location is always better than no location
            return true;
        }

        // check whether the new location fix is newer or older
        final long timeDelta = newLocation.getTime() - mLocation.getTime();
        final boolean isSignificantlyNewer = timeDelta > DEFAULT_DELTA_MILLIS;
        final boolean isSignificantlyOlder = timeDelta < -DEFAULT_DELTA_MILLIS;
        final boolean isNewer = timeDelta > 0;

        // if it's been more than two minutes since the current location, use
        // the new location because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be
            // worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // check whether the new location fix is more or less accurate
        final int accuracyDelta =
            (int) (newLocation.getAccuracy() - mLocation.getAccuracy());
        final boolean isLessAccurate = accuracyDelta > 0;
        final boolean isMoreAccurate = accuracyDelta < 0;
        final boolean isSignificantlyLessAccurate =
            accuracyDelta > SIGNIFICANT_ACCURACY_DELTA;

        // check if the old and new location are from the same provider
        final boolean isFromSameProvider =
            isSameProvider(newLocation.getProvider(), mLocation.getProvider());

        // determine location quality using a combination of timeliness and
        // accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate
            && isFromSameProvider) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether two providers are the same.
     *
     * @param provider1
     *            the first provider to compare.
     * @param provider2
     *            the second provider to compare.
     * @return <code>true</code> if the providers are the same,
     *         <code>false</code> if they aren't.
     */
    private boolean isSameProvider(final String provider1,
            final String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }

        return provider1.equals(provider2);
    }

    /**
     * Abstract method to be overriden, which is called when the location is
     * changed.
     *
     * @param location
     *            the new location.
     */
    protected abstract void updateLocation(Location location);
}
