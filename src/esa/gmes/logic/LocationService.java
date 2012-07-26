package esa.gmes.logic;

import junit.framework.Assert;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Helper class for handling registering and unregistering
 * {@link LocationListener} from updates and polling for location.
 * 
 * @author Jo‹o Xavier <joao.xavier@novabase.pt>
 */
public final class LocationService {

	/**
	 * The calling context, from where to access the {@link LocationManager}
	 * service and to show the location provider errors.
	 */
	private static Context mContext = null;
	/**
	 * The system {@link LocationManager} used to register and unregister the
	 * listeners.
	 */
	private static LocationManager mManager = null;
	/**
	 * The preferred provider according to the best accuracy criteria.
	 */
	private static String mPreferredProvider = null;

	/**
	 * Can't be instantiated.
	 */
	private LocationService() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Instantiates the {@link LocationManager} with a provider according to the
	 * ACCURACY_COARSE {@link Criteria}.
	 */
	private static void initializeProvider() {
		Assert.assertNotNull(mContext);

		if (mManager == null) {
			mManager = (LocationManager) mContext
					.getSystemService(Context.LOCATION_SERVICE);
		}

		updateProvider();
	}

	/**
	 * Set preferred provider based on the best coarse accuracy possible.
	 */
	public static void updateProvider() {
		final Criteria coarseAccuracyCriteria = new Criteria();
		coarseAccuracyCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
		mPreferredProvider = mManager.getBestProvider(coarseAccuracyCriteria,
				true);
	}

	/**
	 * Register the gps listener with the {@link LocationManager} for location
	 * updates.
	 * 
	 * @param gpsListener
	 *            the gps listener to register with location updates.
	 */
	public static void requestGpsUpdates(final LocationListener gpsListener) {
		final String locationProvider = LocationManager.GPS_PROVIDER;
		mManager.requestLocationUpdates(locationProvider, 0, 0, gpsListener);
	}

	/**
	 * If there is no provider, try to initialize it and register the listener
	 * with the {@link LocationManager}. If verbose parameter is set to
	 * <code>true</code>, a {@link Toast} will be shown in case there is no
	 * provider.
	 * 
	 * @param listener
	 *            listener the listener to register with location updates.
	 * @param verbose
	 *            whether a {@link Toast} should be shown in case of an error.
	 * @return <code>true</code> if a provider could be initialized,
	 *         <code>false</code> otherwise.
	 */
	public static boolean requestLocationUpdates(
			final LocationListener listener, final boolean verbose) {
		initializeProvider();

		if (mPreferredProvider == null) {
			if (verbose) {
				showNoLocationProviderError();
			}

			return false;
		}

		mManager.requestLocationUpdates(mPreferredProvider, 0, 0, listener);
		return true;
	}

	/**
	 * Overloads requestLocationUpdates(LocationListener listener, boolean
	 * verbose) assuming verbose as <code>true</code>.
	 * 
	 * @param listener
	 *            the listener to register with location updates.
	 * @return <code>true</code> if a provider could be initialized,
	 *         <code>false</code> otherwise.
	 */
	public static boolean requestLocationUpdates(final LocationListener listener) {
		return requestLocationUpdates(listener, true);
	}

	/**
	 * Stop sending updates to the given listener.
	 * 
	 * @param listener
	 *            the listener to stop sending updates.
	 */
	public static void removeUpdates(final LocationListener listener) {
		if (listener != null)
			mManager.removeUpdates(listener);
	}

	/**
	 * @return the last known location, or <code>null</code> if there isn't one.
	 */
	public static Location getLastKnownLocation() {
		if (mPreferredProvider == null) {
			return null;
		}

		return mManager.getLastKnownLocation(mPreferredProvider);
	}

	/**
	 * Displays a {@link Toast} notifying the user that there's no available
	 * location provider.
	 */
	public static void showNoLocationProviderError() {
		// no
	}

	/**
	 * Assign a mContext to the singleton class. Must be called before any other
	 * method.
	 * 
	 * @param context
	 *            the context to be set.
	 */
	public static void setContext(final Context context) {
		if (LocationService.mContext == null) {
			LocationService.mContext = context;
		}
	}

	/**
	 * Whether the location can be retrieved or not, i.e., whether there is an
	 * available location provider.
	 * 
	 * @return <code>true</code> if the location can be retrieved,
	 *         <code>false</code> otherwise.
	 */
	public static boolean canRetrieveLocation() {
		return mPreferredProvider != null;
	}
}
