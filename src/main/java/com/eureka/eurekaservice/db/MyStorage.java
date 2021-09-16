package com.eureka.eurekaservice.db;

import com.eureka.eurekaservice.entity.SccoRecord;

import java.util.ArrayList;
import java.util.List;

public class MyStorage {

	private static MyStorage instance;
	private static List<SccoRecord> listRecord = new ArrayList();

	public static MyStorage getInstance() {
		if (instance == null) {
			instance = new MyStorage();
		}
		return instance;
	}

	public List<SccoRecord> getListRecord() {
		return listRecord;
	}

	public void setListRecord(List<SccoRecord> listRecord) {
		MyStorage.listRecord = listRecord;
	}

	public void addRecord(SccoRecord record) {
		listRecord.add(record);
	}
}
