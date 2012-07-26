package esa.gmes.activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import esa.gmes.R;
import esa.gmes.activity.base.LocationAwareActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainscreenActivity extends LocationAwareActivity {

	private static final String TAG = "MainscreenActivity";
	private static final int TAKE_PICTURE = 10000001;
	private static final String CAPTURE_TITLE = "test.png";

	public static Handler handler = new Handler();
	 public static BufferedReader in;
	 private static File file;
	 public static PrintWriter out;
	 boolean listening;
	 private String message;
	 static String hash_name;
	 
    Socket socket;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		Button btnShare = (Button) findViewById(R.id.btn_share);
		Button btnQuiz = (Button) findViewById(R.id.btn_quiz);
		Button btnNearMe = (Button) findViewById(R.id.btn_near_me);
		Button btnNotifications = (Button) findViewById(R.id.btn_notifications);
		ImageView imgProfile = (ImageView) findViewById(R.id.img_profile);
		startCon();

		initializeLocationAwareness();

		btnShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
				startActivityForResult(cameraIntent, TAKE_PICTURE);
			}
		});
		
		imgProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainscreenActivity.this, "Not available in the demo version.", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnNotifications.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainscreenActivity.this, "Not available in the demo version.", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnNearMe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent nearMeIntent = new Intent(MainscreenActivity.this, NearMeActivity.class);
				startActivity(nearMeIntent);
			}
		});
		
		btnQuiz.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent quizIntent = new Intent(MainscreenActivity.this, QuizQuestionActivity.class);
				startActivity(quizIntent);
			}
		});
	}
	
	private Uri getImageUri() {
		 // Store image in dcim
	    file = new File(Environment.getExternalStorageDirectory() + "/DCIM", CAPTURE_TITLE);
	    android.util.Log.d("TAG", file + "");
	    
	    Uri imgUri = Uri.fromFile(file);

	    return imgUri;
	}
	
	public void startCon(){
	     try{

	      Thread thread = new Thread(null, doBackgroundThreadProcessing, "Background");
	      thread.start();
	     }
	     catch (Exception e){
	      System.out.println("Server not found!");
	     }
	    }
	    private Runnable doBackgroundThreadProcessing = new Runnable() {

	     public void run() {
	      try{ 
		      System.out.println("SocketsOK3333");

		    InetAddress serverAddr= InetAddress.getByName("192.168.1.6");//"89.210.156.206");//195.251.166.50  

	     	// InetAddress serverAddr= InetAddress.getByName("10.0.2.2");//"89.210.156.206");//195.251.166.50  
	       try{
			      System.out.println("SocketsOK");

	        socket = new Socket(serverAddr,6500);
		      System.out.println("SocketsOK2");
	       
	       // send - receive
	       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	       out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);

	       }catch (Exception e){

	          }
	       hash_name=hash_User("UserNamePassword");
		      System.out.println("Prin stilo");

	    out.println("Connect$"+hash_name);
	   // out.println("Points "+hash_name+" 1");
	   

	    listening = true;
	          backgroundThreadProcessing();
	          
	      }catch (Exception e){
	    	  System.out.println("Server not found!"+e.getMessage());
	      }
	     }
	    };
	    private void backgroundThreadProcessing() {
		      System.out.println("Eimai edo!!!");

	     while(listening){
	   try{
		   
	    message=in.readLine(); 
	    if(message!=null){
	     handler.post(doUpdateGUI);
	    }
	    else{     
	     listening=false;
	    } 
	   }
	   catch(Exception e){
	    listening=false;
	    e.printStackTrace();        
	   }
	  }
	      }
	    private Runnable doUpdateGUI = new Runnable() {
	        public void run() {
	         @SuppressWarnings("unused")
	         Context context = getApplicationContext();
	         handleMessage(message);
	        }
	      };
	      
	      public void handleMessage(String mess){  
	    	  
	    	         if(mess.equalsIgnoreCase("Connected")){
	    	          try{
	    	        	  Toast msg = Toast.makeText(MainscreenActivity.this, "Connected", Toast.LENGTH_LONG);
	    	        	  msg.show();
	    	          }
	    	          catch(Exception e){
	    	           System.out.println("Exception: "+e);
	    	          }
	    	         }
	    	        
	    	        
	    	         else if(mess.equalsIgnoreCase("Points")){
	    	          try{
	    	        	  int first$ = mess.indexOf('$');
	    	          	System.out.println(first$);

	    	          	String subject = mess.substring(0,first$);
	    	          	String message = mess.substring(first$+1,mess.length());

	    	          	System.out.println(subject);
	    	          	System.out.println(message);
	    	        	  Toast msg = Toast.makeText(MainscreenActivity.this, message, Toast.LENGTH_LONG);

	    	     
	    	          }
	    	          catch(Exception e){
	    	           System.out.println("Exception: "+e);
	    	          }
	    	            }
	    	         else if(mess.equalsIgnoreCase("Secure")){
		    	          try{
		    	        	  int first$ = mess.indexOf('$');
			    	          	System.out.println(first$);

			    	          	String subject = mess.substring(0,first$);
			    	          	String message = mess.substring(first$+1,mess.length());

			    	          	System.out.println(subject);
			    	          	System.out.println(message);
		    	        	  Toast msg = Toast.makeText(MainscreenActivity.this, "New Achievement", Toast.LENGTH_LONG);

		    	     
		    	          }
		    	          catch(Exception e){
		    	           System.out.println("Exception: "+e);
		    	          }
		    	            }
	    	           
	    	        }

	      
	      public static void point(){
		 	    out.println("Points$GetThePoints");


		      }
	      public static void share(String type1) throws IOException{
	    	  MainscreenActivity.sendPhoto();
	    	  
	    	 String userid=hash_name;
	     	 String log="41.827114";
	     	 String lat="12.673277";
	     	 String type=type1;
	     	 SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
	     	 String format = s.format(new Date());
	     	 String time=format;
	 	    out.println("Share$"+userid+"$"+log+"$"+lat+"$"+type+"$"+time);

	      }
	      
	      public static void sendPhoto() throws IOException{
	    		 InetAddress server2=InetAddress.getByName("10.0.2.2");

	    	  Socket photo_socket = new Socket(server2,6501);
	    		try{
	    			ObjectOutputStream out = new ObjectOutputStream(photo_socket.getOutputStream());	    				
	    				
	    			InputStream is = new FileInputStream(file);
					long file_length = file.length();
					int ok=1;
					byte[] myarray = new byte[(int)file_length];
					int offset = 0;
	
					int numRead = 0;
					while (offset < myarray.length && (numRead=is.read(myarray, offset, myarray.length-offset)) >= 0) {
						offset += numRead;
					}
					if (offset < myarray.length) {
						ok=0;
						throw new IOException("Could not completely read file ");
					}	
	

					if (ok==1) {
						out.writeObject(myarray);
						out.flush();
					}
					is.close();
					
	    			
	    		}//try
	    	            catch(Exception e)
	    	            {
	    	                e.printStackTrace();
	    	            }//catch
		      }
	      
	      public static String hash_User(String mess) throws NoSuchAlgorithmException{
	    	    MessageDigest md = MessageDigest.getInstance("SHA");
	    	    md.update(mess.getBytes());
	    	    byte byteData[] = md.digest();
	    	    StringBuffer hexString = new StringBuffer();
	    		for (int i=0;i<byteData.length;i++) {
	    			String hex=Integer.toHexString(0xff & byteData[i]);
	    		     	if(hex.length()==1) hexString.append('0');
	    		     	hexString.append(hex);
	    		}
	    		String ma= hexString.toString();
	    		return ma;
	      }


	@Override
	protected void updateLocation(Location location) {
		if (location != null) {
			Log.d(TAG, "got location");
	//		txtLocation.setText("lat: " + location.getLatitude()
	//				+ ", lon: " + location.getLongitude());
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		removeLocationUpdates();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == TAKE_PICTURE) && (resultCode == Activity.RESULT_OK)) {
			Log.d(TAG, "camera activity finished succesfully");

			if (data == null) {
				Intent newIntent = new Intent(this, ShareActivity.class);
				newIntent.putExtra("file", file);
				
				android.util.Log.d("TAG", file + "");
				android.util.Log.d("TAG", file + "" + " " + file.getName());
				startActivity(newIntent);
			}
		}
	}
}