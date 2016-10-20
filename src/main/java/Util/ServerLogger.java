package Util;

import org.mariadb.jdbc.internal.logging.LoggerFactory;

/**
 * Created by Anders Egberts on 19/10/2016.
 */
public class ServerLogger {
    public static void log( Class instigatorClass , String msg ) {
        //org.apache.juli.logging.LogFactory.getLog( instigatorClass ).info( msg );
        LoggerFactory.getLogger( instigatorClass ).info( msg );
    }
}