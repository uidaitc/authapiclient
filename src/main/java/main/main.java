package main;

import auth_2_0.Auth;
import auth_2_0.AuthRes;
import constants.ApplicationProperties;
import auth_2_0.AuthResponseDetailsV2;

import java.text.SimpleDateFormat;
import java.util.Date;


public class main {

    public static void main(String[] args) {
        OTPAuth OTPAuth = new OTPAuth();
        try {
            HelperClass helperClass = new HelperClass(new ApplicationProperties());
            Auth auth = OTPAuth.createResidentAuth("999967367882", "uuid", "766175", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()), "");
            AuthResponseDetailsV2 data = helperClass.getAuthResponseDetailsV2(auth);

            // Get Auth Response
            AuthRes authRes = data.getAuthRes();
            System.out.println(authRes.getRet().toString());
            System.out.println(authRes.getErr());

        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
