package com.ztw.ztw.utils;

import com.ztw.ztw.ZtwApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {
    public static String getEmail(Principal principal){
        Map<String, Object> authDetails = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        return (String) authDetails.get("email");
    }

    public static String parseDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String parsed = dateFormat.format(date);
        ZtwApplication.logger.info(parsed);
        return parsed;
    }
}
