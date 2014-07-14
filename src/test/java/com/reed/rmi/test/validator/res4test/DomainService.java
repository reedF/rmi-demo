package com.reed.rmi.test.validator.res4test;

public interface DomainService {

	int save(User4ValidTest bean);
	
	int saveCascade(User4ValidTest bean1,Udept4ValidTest bean2) ;

	int saveCascade(User4ValidTest bean1,User4ValidTest bean2) ;
	
	int update(int id,String name,String email);

	int update(User4ValidTest bean);
	
	int updateCascade(User4ValidTest bean1,Udept4ValidTest bean2) ;
	
	int saveMulti(int serialNo,User4ValidTest user,String desc,Udept4ValidTest ddept,StringBuffer strBuf) ;
	
	int execute4defaultGroup(User4ValidTest bean1,Udept4ValidTest bean2) ;
	
}
