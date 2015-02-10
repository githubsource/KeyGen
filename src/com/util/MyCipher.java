package com.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class MyCipher {
	public static PrivateKey loadPrivateKey(String key64) throws GeneralSecurityException, IOException {
		BASE64Decoder base64Decoder=new BASE64Decoder();
	    byte[] clear = base64Decoder.decodeBuffer(key64);
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    PrivateKey priv = fact.generatePrivate(keySpec);
	    Arrays.fill(clear, (byte) 0);
	    return priv;
	}


	public static PublicKey loadPublicKey(String stored) throws GeneralSecurityException, IOException {
		BASE64Decoder base64Decoder=new BASE64Decoder();
	    byte[] data = base64Decoder.decodeBuffer(stored);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    return fact.generatePublic(spec);
	}

	public static String savePrivateKey(PrivateKey priv) throws GeneralSecurityException {
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    PKCS8EncodedKeySpec spec = (PKCS8EncodedKeySpec) fact.getKeySpec(priv, PKCS8EncodedKeySpec.class);
	    byte[] packed = spec.getEncoded();
	    
	    BASE64Encoder base64Encode = new BASE64Encoder();
	    String key64 = base64Encode.encode(packed);

	    Arrays.fill(packed, (byte) 0);
	    return key64;
	}


	public static String savePublicKey(PublicKey publ) throws GeneralSecurityException {
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    X509EncodedKeySpec spec = (X509EncodedKeySpec) fact.getKeySpec(publ,X509EncodedKeySpec.class);
	    BASE64Encoder base64Encode = new BASE64Encoder();
	    return base64Encode.encode(spec.getEncoded());
	}


	public static void main(String[] args) throws Exception {
	    KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
	    KeyPair pair = gen.generateKeyPair();

	    String pubKey = savePublicKey(pair.getPublic());
	    PublicKey pubSaved = loadPublicKey(pubKey);
	    System.out.println(pair.getPublic()+"\n"+pubSaved);
	    
	    System.out.println("======================00000============");
	    
	    String privKey = savePrivateKey(pair.getPrivate());
	    PrivateKey privSaved = loadPrivateKey(privKey);
	    System.out.println(pair.getPrivate()+"\n"+privSaved);
	}
}
