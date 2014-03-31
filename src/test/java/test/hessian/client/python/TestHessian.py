#!/usr/bin/env python   
#coding=utf-8   
# -*- coding:utf-8 -*-   
###   
### Hessian client
###   
########################################   
  
#import hessianlib
  
if __name__ == '__main__':   
    #proxy = hessianlib.Hessian('http://localhost:8080/rmi-demo/remoting/userServiceImpl')  
    from mustaine.client import HessianProxy
    proxy = HessianProxy('http://localhost:8080/rmi-demo/remoting/userServiceImpl')  
    print proxy.findById(long(303)).name
    print 'Done',  
