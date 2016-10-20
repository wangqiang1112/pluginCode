package demo;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
public class Aes2 {
       
   
    private static final String KEY_ALGORITHM = "AES";
       
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
       
   
    public static byte[] initSecretKey() {
        //��������ָ���㷨��������Կ�� KeyGenerator ����
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //��ʼ������Կ��������ʹ�����ȷ������Կ��С
        //AES Ҫ����Կ����Ϊ 128
        kg.init(128);
        //����һ����Կ
        SecretKey  secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
       
   
    private static Key toKey(byte[] key){
        //������Կ
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }
       
   
    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
       
   
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //��ԭ��Կ
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }
       
   
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //ʵ����
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //ʹ����Կ��ʼ��������Ϊ����ģʽ
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //ִ�в���
        return cipher.doFinal(data);
    }
       
       
       
   
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //��ԭ��Կ
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }
   
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //ʵ����
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //ʹ����Կ��ʼ��������Ϊ����ģʽ
        cipher.init(Cipher.DECRYPT_MODE, key);
        //ִ�в���
        return cipher.doFinal(data);
    }
       
    private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
       
    public static void main(String[] args) throws Exception {
        byte[] key = initSecretKey();
        System.out.println("key��"+showByteArray(key));
           
        Key k = toKey(key);
           
        String data ="AES����";
        System.out.println("����ǰ����: string:"+data);
        System.out.println("����ǰ����: byte[]:"+showByteArray(data.getBytes()));
        System.out.println();
        byte[] encryptData = encrypt(data.getBytes(), k);
        System.out.println("���ܺ�����: byte[]:"+showByteArray(encryptData));
//        System.out.println("���ܺ�����: hexStr:"+Hex.encodeHexStr(encryptData));
        System.out.println();
        byte[] decryptData = decrypt(encryptData, k);
        System.out.println("���ܺ�����: byte[]:"+showByteArray(decryptData));
        System.out.println("���ܺ�����: string:"+new String(decryptData));
           
    }
 
}
