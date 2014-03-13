package com.demo.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES {

		Key key;

		public DES(String str) {
			setKey(str);//�����ܳ�
		}

		public DES() {
			setKey("siyue_qi");
		}

		/**
		 * ���ݲ�������KEY
		 */
		public void setKey(String strKey) {
			try {
				KeyGenerator _generator = KeyGenerator.getInstance("DES");
				_generator.init(new SecureRandom(strKey.getBytes()));
				this.key = _generator.generateKey();
				_generator = null;
			} catch (Exception e) {
				throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			}
		}

		/**
		 * ����String��������,String�������
		 */
		public String getEncString(String strMing) {
			byte[] byteMi = null;
			byte[] byteMing = null;
			String strMi = "";
			BASE64Encoder base64en = new BASE64Encoder();
			try {
				byteMing = strMing.getBytes("UTF-8");
				byteMi = this.getEncCode(byteMing);
				strMi = base64en.encode(byteMi);
			} catch (Exception e) {
				throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			} finally {
				base64en = null;
				byteMing = null;
				byteMi = null;
			}
			return strMi;
		}

		/**
		 * ���� ��String��������,String�������
		 * @param strMi
		 * @return
		 */
		public String getDesString(String strMi) {
			BASE64Decoder base64De = new BASE64Decoder();
			byte[] byteMing = null;
			byte[] byteMi = null;
			String strMing = "";
			try {
				byteMi = base64De.decodeBuffer(strMi);
				byteMing = this.getDesCode(byteMi);
				strMing = new String(byteMing, "UTF-8");
			} catch (Exception e) {
				throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			} finally {
				base64De = null;
				byteMing = null;
				byteMi = null;
			}
			return strMing;
		}

		/**
		 * ������byte[]��������,byte[]�������
		 * @param byteS
		 * @return
		 */
		private byte[] getEncCode(byte[] byteS) {
			if(true)
				return byteS;
			byte[] byteFina = null;
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				byteFina = cipher.doFinal(byteS);
			} catch (Exception e) {
				throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			} finally {
				cipher = null;
			}
			return byteFina;
		}

		/**
		 * ������byte[]��������,��byte[]�������
		 * @param byteD
		 * @return
		 */
		private byte[] getDesCode(byte[] byteD) {
			if(true)
				return byteD;
			Cipher cipher;
			byte[] byteFina = null;
			try {
				cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.DECRYPT_MODE, key);
				byteFina = cipher.doFinal(byteD);
			} catch (Exception e) {
				throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			} finally {
				cipher = null;
			}
			return byteFina;
		}

		public static void main(String args[]) {
			DES des = new DES();
			// ������Կ
			des.setKey("12345678");

			String str1 = "�����й���";
			//DES����
			String str2 = des.getEncString(str1);
			String deStr = des.getDesString(str2);
			
			//DES����
			
		}
}
