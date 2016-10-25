package com.yitong.app.action.judicial.fysfck;

public class SyyhConvertDataHandleProxy implements SyyhConvertDataHandle {
  private String _endpoint = null;
  private SyyhConvertDataHandle syyhConvertDataHandle = null;
  
  public SyyhConvertDataHandleProxy() {
    _initSyyhConvertDataHandleProxy();
  }
  
  public SyyhConvertDataHandleProxy(String endpoint) {
    _endpoint = endpoint;
    _initSyyhConvertDataHandleProxy();
  }
  
  private void _initSyyhConvertDataHandleProxy() {
    try {
      syyhConvertDataHandle = (new SyyhConvertDataServiceLocator()).getSyyhConvertDataPort();
      if (syyhConvertDataHandle != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)syyhConvertDataHandle)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)syyhConvertDataHandle)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (syyhConvertDataHandle != null)
      ((javax.xml.rpc.Stub)syyhConvertDataHandle)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SyyhConvertDataHandle getSyyhConvertDataHandle() {
    if (syyhConvertDataHandle == null)
      _initSyyhConvertDataHandleProxy();
    return syyhConvertDataHandle;
  }
  
  public java.lang.String shfeedXzcxInfo(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (syyhConvertDataHandle == null)
      _initSyyhConvertDataHandleProxy();
    return syyhConvertDataHandle.shfeedXzcxInfo(arg0, arg1);
  }
  
  public java.lang.String getXzcxList(java.lang.String arg0) throws java.rmi.RemoteException{
    if (syyhConvertDataHandle == null)
      _initSyyhConvertDataHandleProxy();
    return syyhConvertDataHandle.getXzcxList(arg0);
  }
  
  
}