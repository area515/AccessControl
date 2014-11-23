package org.area515.security.accesscontrol.domain;

public class SourceAddress {

	String networkInterface;

	public String getNetworkInterface() {
		return this.networkInterface;
	}

	public void setInterface(String networkInterface) {
		this.networkInterface = networkInterface;
	}

	String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SourceAddress() {
	}

	public SourceAddress(String address) {
		this.address = address;
	}

	public SourceAddress(String networkInterface, String address) {
		this.networkInterface = networkInterface;
		this.address = address;
	}
}
