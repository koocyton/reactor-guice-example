package com.doopp.gauss.oauth.defined;

import com.doopp.reactor.guice.RequestAttribute;
import io.netty.util.AttributeKey;

public class CommonField {

    public static String SESSION_KEY  = "se_id";

    public static String CURRENT_USER  = "current_user";

    public static String CURRENT_CHANNEL  = "current_channel";

    public static AttributeKey<RequestAttribute> REQUEST_ATTRIBUTE = AttributeKey.newInstance("request_attribute");
}
