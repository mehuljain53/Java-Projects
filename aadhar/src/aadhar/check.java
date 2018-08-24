package aadhar;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.omg.CORBA.portable.ResponseHandler;

 class check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String adnom="960713102467";
		
	
		//HttpsURLConnection 
		

	}

	public void requestotp(String aadhar)
	{
		 String son1 = "{ \"aadhaar-id\": \""+aadhar+"\", \"channel\":\"SMS\", \"location\": { \"type\": \"gps\", \"latitude\": \"73.2\", \"longitude\": \"22.3\", \"altitude\": \"0\" } }";

         String[] inputArr = new String[] {
                 aadhar,
                 son1,
         };
         
         
	}
	public static class RequestOTPTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            Log.d("I am here", "YE");
            //get the various required fields

            String son1 = params[1];

            Boolean otp_sent = false;
            DefaultHttpClient client = new DefaultHttpClient();
            HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            String result[] = new String[1];
            //adding ssl capabilities
            SchemeRegistry registry = new SchemeRegistry();
            SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
            registry.register(new Scheme("https", socketFactory, 443));
            SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);

            DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());


            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit



            HttpResponse response;

            try {
                HttpPost post = new HttpPost("https://ac.khoslalabs.com/hackgate/hackathon/otp");
                StringEntity se = new StringEntity(son1);
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);
                response = httpClient.execute(post);

                /*Checking response */
                if(response!=null){
                    Log.d("RESPONSE", "FOUND");
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    Log.d("stream", body);
                    if(body.indexOf("success\":true") > 0) {
                        otp_sent= true;
                        result[0] = "Sent!";
                    }
                    if(body.indexOf("success\":false") > 0) {
                        otp_sent= true;
                        result[0] = "Invalid Aadhar Number!";
                    }

                    Log.d("AsyncTask", "done done Details Correct");
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
            if(otp_sent==true) {Log.d("AsyncTask", "OTP succesfully sent");return result; }


            result[0] = "Check Connectivity.";
            return result;

        }

    }
}
