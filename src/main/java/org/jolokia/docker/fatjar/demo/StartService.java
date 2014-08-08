package org.jolokia.docker.fatjar.demo;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.jolokia.http.AgentServlet;

/**
 * Popup Tomcat and install Jolokia
 * @author roland
 * @since 08.08.14
 */
public class StartService {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        File base = new File(System.getProperty("java.io.tmpdir"));
        Context rootCtx = tomcat.addContext("/", base.getAbsolutePath());
        Tomcat.addServlet(rootCtx, "jolokia", new AgentServlet());
        rootCtx.addServletMapping("/jolokia/*", "jolokia");
        tomcat.start();
        tomcat.getServer().await();
    }
}
