package main;

import auth_2_0.*;
import auth_2_0.types.AuthRcType;
import auth_2_0.types.DataFormat;
import auth_2_0.types.UsesType;
import constants.ApplicationProperties;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.exolab.castor.xml.ValidationException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.StringWriter;
import java.rmi.MarshalException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Date;

public class OTPAuth {

    public ApplicationProperties applicationProperties = new ApplicationProperties();
    HelperClass helperClass = new HelperClass(applicationProperties);

    public Auth createResidentAuth(String uid, String txn, String otpInRequest, String timeStamp, String mobile) throws ValidationException, NoSuchProviderException, NoSuchAlgorithmException, InvalidCipherTextException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, org.exolab.castor.xml.MarshalException {
        Security.addProvider(new BouncyCastleProvider()); // added
        Auth auth = new Auth();

        auth.setVer(this.applicationProperties.getAuthReqVersion());
        auth.setRc(AuthRcType.Y);
        auth.setAc(this.applicationProperties.getAuthRequestAua());
        auth.setLk(this.applicationProperties.getAuthReqAuaLK());
        auth.setSa(this.applicationProperties.getAuthRequestSa());
        auth.setTid("");
        auth.setTxn(txn);
        auth.setUid(uid);

        // pid
        Date pidDate = new Date();
        String pidXml = helperClass.createPid(otpInRequest, pidDate, mobile);
        byte[] sessionKey = helperClass.generateSessionKey();

        // hmac
        byte[] hmac = helperClass.generateHash(pidXml);
        byte[] encryptedHmac = helperClass.encrypt(hmac, sessionKey, timeStamp.getBytes());
        auth.setHmac(encryptedHmac);

        // uses
        Uses uses = new Uses();
        uses.setBio(UsesType.N);
        uses.setOtp(UsesType.Y);
        uses.setPa(UsesType.N);
        uses.setPfa(UsesType.N);
        if (mobile != null && !mobile.equals("")) {
            uses.setPi(UsesType.Y);
        } else {
            uses.setPi(UsesType.N);
        }
        uses.setPin(UsesType.N);
        uses.setBt("");
        auth.setUses(uses);

        // meta
        Meta meta = new Meta();
        meta.setDc("");
        meta.setDpId("");
        meta.setMc("");
        meta.setMi("");
        meta.setRdsId("");
        meta.setRdsVer("");
        /* meta.setUdc("VIDGeneration"); */
        auth.setMeta(meta);

        // s key
        Skey skey = new Skey();
        skey.setContent(helperClass.encrypt(sessionKey, applicationProperties.getAuthReqPubKeyFile()));
        skey.setCi(helperClass.getCI());
        auth.setSkey(skey);

        // data
        Data data = new Data();
        data.setType(DataFormat.X);
        byte[] encryptedBytes = helperClass.encrypt(pidXml.getBytes(), sessionKey, timeStamp.getBytes());
        byte[] encryptedPid = ArrayUtils.addAll(timeStamp.getBytes(), encryptedBytes);
        data.setContent(encryptedPid);
        auth.setData(data);

        StringWriter sw1 = new StringWriter();
        auth.marshal(sw1);
        return auth;
    }

}

