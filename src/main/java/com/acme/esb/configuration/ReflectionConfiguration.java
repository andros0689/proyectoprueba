package com.acme.esb.configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.ValidationException;

import jakarta.xml.bind.MarshalException;
import jakarta.xml.bind.UnmarshalException;
import javax.xml.xpath.XPathExpressionException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
@RegisterForReflection(targets = {
        ConnectException.class,
        SQLRecoverableException.class,
        SQLSyntaxErrorException.class,
        SQLException.class,
        NullPointerException.class,
        InvalidFormatException.class,
        UnmarshalException.class,
        JsonParseException.class,
        MarshalException.class,
        ValidationException.class,
        XPathExpressionException.class,
        String.class,
        SocketTimeoutException.class,
        java.lang.ClassCastException.class
})
public class ReflectionConfiguration {
}
