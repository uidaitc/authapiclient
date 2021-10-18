package constants;


public class ApplicationProperties {
    private String authRequestAua = "public";
    private String authRequestSa = "public";
    private String authReqAsaLK = "MEY2cG1nhC02dzj6hnqyKN2A1u6U0LcLAYaPBaLI-3qE-FtthtweGuk";
    private String authReqAuaLK = "MAElpSz56NccNf11_wSM_RrXwa7n8_CaoWRrjYYWouA1r8IoJjuaGYg";
    private String authReqVersion = "2.5";
    private String authReqDigSigPassword = "public";
    private String authReqDigSigKeyAlias = "PublicAUAforStagingServices";
    private String authReqTerminal = "public";
    private String authReqUrl = "http://10.5.96.111:8009/uidauthserver/2.5";
    private String authReqDigSigFile = "/home/sambit/Desktop/PublicAUAforStagingServices.p12";
    private String authReqPubKeyFile = "/home/sambit/Desktop/AuthStaging25082025.cer";
    private String pidVersion = "2.0";


    public String getAuthRequestAua() {
        return authRequestAua;
    }

    public String getAuthRequestSa() {
        return authRequestSa;
    }

    public String getAuthReqAsaLK() {
        return authReqAsaLK;
    }

    public String getAuthReqAuaLK() {
        return authReqAuaLK;
    }

    public String getAuthReqVersion() {
        return authReqVersion;
    }

    public String getAuthReqDigSigPassword() {
        return authReqDigSigPassword;
    }

    public String getAuthReqDigSigKeyAlias() {
        return authReqDigSigKeyAlias;
    }

    public String getAuthReqUrl() {
        return authReqUrl;
    }

    public String getAuthReqDigSigFile() {
        return authReqDigSigFile;
    }

    public String getAuthReqPubKeyFile() {
        return authReqPubKeyFile;
    }

    public String getPidVersion() {
        return pidVersion;
    }

}
