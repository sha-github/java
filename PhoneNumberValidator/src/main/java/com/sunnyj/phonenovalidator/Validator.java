package com.sunnyj.phonenovalidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Validator {

	public static void main(String[] args) throws NumberParseException {
		String numberStr = "+913333333333";
		if (numberStr.startsWith("+")) {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber numberProto = phoneUtil.parse(numberStr,
					PhoneNumberFormat.INTERNATIONAL.name());
			System.out.println(numberProto.getNationalNumber());
		} else {
			System.out.println(numberStr);
		}
	}
}
