/**
 * SyyhConvertDataServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.yitong.app.action.judicial.fysfck;

public class SyyhConvertDataServiceLocator extends org.apache.axis.client.Service implements SyyhConvertDataService {

    public SyyhConvertDataServiceLocator() {
    }


    public SyyhConvertDataServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SyyhConvertDataServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SyyhConvertDataPort
    private java.lang.String SyyhConvertDataPort_address = "http://localhost:8084/siFaService/services/SiFaRequest";

    public java.lang.String getSyyhConvertDataPortAddress() {
        return SyyhConvertDataPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SyyhConvertDataPortWSDDServiceName = "SyyhConvertDataPort";

    public java.lang.String getSyyhConvertDataPortWSDDServiceName() {
        return SyyhConvertDataPortWSDDServiceName;
    }

    public void setSyyhConvertDataPortWSDDServiceName(java.lang.String name) {
        SyyhConvertDataPortWSDDServiceName = name;
    }

    public SyyhConvertDataHandle getSyyhConvertDataPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SyyhConvertDataPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSyyhConvertDataPort(endpoint);
    }

    public SyyhConvertDataHandle getSyyhConvertDataPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
           SyyhConvertDataServiceSoapBindingStub _stub = new SyyhConvertDataServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSyyhConvertDataPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSyyhConvertDataPortEndpointAddress(java.lang.String address) {
        SyyhConvertDataPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SyyhConvertDataHandle.class.isAssignableFrom(serviceEndpointInterface)) {
                SyyhConvertDataServiceSoapBindingStub _stub = new SyyhConvertDataServiceSoapBindingStub(new java.net.URL(SyyhConvertDataPort_address), this);
                _stub.setPortName(getSyyhConvertDataPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SyyhConvertDataPort".equals(inputPortName)) {
            return getSyyhConvertDataPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://syyh.service.webService.ckw.tdh/", "SyyhConvertDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://syyh.service.webService.ckw.tdh/", "SyyhConvertDataPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SyyhConvertDataPort".equals(portName)) {
            setSyyhConvertDataPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
