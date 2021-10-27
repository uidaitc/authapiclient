package main;

import auth_2_0.Auth;
import auth_2_0.AuthRes;
import constants.Constants;
import auth_2_0.AuthResponseDetailsV2;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    public static void main(String[] args) {
        OTPAuth OTPAuth = new OTPAuth();
        try {
            OTPAuth.readProperties();
            HelperClass helperClass = new HelperClass(OTPAuth.configProp);

            String uid = args[0];
            String txn = args[1];
            String otpInRRequest = args[2];
            System.out.println("UID: "+uid+" TxnId: "+txn+" OTP :"+otpInRRequest);
            Auth auth = OTPAuth.createResidentAuth(uid, txn, otpInRRequest, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()), "");
            AuthResponseDetailsV2 data = helperClass.getAuthResponseDetailsV2(auth);

            // Get Auth Response
            AuthRes authRes = data.getAuthRes();
            System.out.println("Auth Response: " + authRes.getRet().toString());
            if (authRes.getErr() != null)
            System.out.println("ErrorCode: "+authRes.getErr());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
