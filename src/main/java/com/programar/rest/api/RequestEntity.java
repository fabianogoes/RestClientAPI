package com.programar.rest.api;

import org.springframework.http.HttpStatus;

/**
 * Client API Rest
 * 
 * @author Fabiano Góes - 26/05/2016
 * @category REST
 * @version 1.0
 *
 */
public class RequestEntity {

	private ProtocolType protocol = ProtocolType.HTTP;
	private String hostServer;
	private int hostPort = 80;
	private String pathTarget;
	private String pathComplete;

	public RequestEntity() {
		this.formatPathComplete();
	}

	/**
	 * 
	 * @param protocol
	 *            : HTTP, HTTPS
	 * @param hostServer
	 *            : Example google.com.br, eprogramar.com
	 * @param pathTarget
	 *            : Example /, /create, /123/456
	 * @param pathComplete
	 *            : http://eprogramar.com/123
	 */
	public RequestEntity(ProtocolType protocol, String hostServer, int hostPort, String pathTarget) {
		this(protocol, hostServer, hostPort);
		this.pathTarget = pathTarget;
		this.formatPathComplete();
	}

	public RequestEntity(ProtocolType protocol, String hostServer, int hostPort) {
		this(hostServer, hostPort);
		this.protocol = protocol;
		this.formatPathComplete();
	}
	
	public RequestEntity(String hostServer, int hostPort) {
		this.hostServer = hostServer;
		this.hostPort = hostPort;
		this.formatPathComplete();
	}
	
	/**
	 * 
	 * @param pathTarget
	 *            example: /123
	 */
	public void setPathTarget(String pathTarget) {
		this.pathTarget = pathTarget;
		this.formatPathComplete();
	}

	/**
	 * 
	 * @param hostServer
	 *            example: eprogramar.com
	 */
	public void setHostServer(String hostServer) {
		this.hostServer = hostServer.replace("http", "").replace("https", "").replace(":", "").replace("/", "");
		this.formatPathComplete();
	}

	/**
	 * 
	 * @param protocol
	 *            HTTP, HTTPS
	 */
	public void setProtocol(ProtocolType protocol) {
		this.protocol = protocol;
	}

	private void formatPathComplete() {
		this.pathComplete = this.protocol.name().toLowerCase() + "//" + this.hostServer
				+ (this.hostPort == 80 ? "" : ":" + String.valueOf(this.hostPort)) + this.pathTarget;
		;
	}

	/**
	 * 
	 * @return example: http://eprogramar.com/123
	 */
	public String getPathComplete() {
		this.pathComplete = this.hostServer + this.pathTarget;
		return this.pathComplete;
	}

	/**
	 * 
	 * @param hostPort
	 *            example: 80, 8080
	 */
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
		this.formatPathComplete();
	}

	@Override
	public String toString() {
		this.formatPathComplete();
		return "RestEntity [protocol=" + protocol + ", hostServer=" + hostServer + ", pathTarget=" + pathTarget
				+ ", pathComplete=" + pathComplete + "]";
	}

	public ProtocolType getProtocol() {
		return protocol;
	}

	public String getHostServer() {
		return hostServer;
	}

	public int getHostPort() {
		return hostPort;
	}

	public String getPathTarget() {
		return pathTarget;
	}

	public void setPathComplete(String pathComplete) {
		this.pathComplete = pathComplete;
	}

	public static void main(String[] args) {
		RequestEntity restEntity = new RequestEntity();
		restEntity.setHostServer("http://93.188.161.66/");
		restEntity.setHostPort(6917);
		restEntity.setPathTarget("/");

		System.out.println(restEntity);
		
		HttpStatus httpStatus = HttpStatus.valueOf(511);
		System.out.println( httpStatus.name() );
	}
}
