import java.util.*;
import java.math.BigInteger;
import java.lang.*;

class RSAkeygen
{
  public static void main(String[] args)
  {
    // Generate two random numbers using current system time
    Random rand1=new Random(System.currentTimeMillis());
    Random rand2=new Random(System.currentTimeMillis()*10);

    //accept command line argument about public key
    int pubkey=Integer.parseInt(args[0]);

    // to Generate two very large prime numbers, p and q
    // The random numbers are given as a seed value.
    BigInteger bigB_p=BigInteger.probablePrime(32, rand1);
    BigInteger bigB_q=BigInteger.probablePrime(32, rand2);

    BigInteger bigB_n=bigB_p.multiply(bigB_q);

    // Find p-1 and q-1
    BigInteger bigB_p_1=bigB_p.subtract(new BigInteger("1"));
    BigInteger bigB_q_1=bigB_q.subtract(new BigInteger("1"));

    BigInteger bigB_p_1_q_1=bigB_p_1.multiply(bigB_q_1);

    // Find a number such that
    while(true)
    {
      // Generate e such that gcd(e, phi) = 1
      BigInteger BigB_GCD=bigB_p_1_q_1.gcd(new BigInteger(""+pubkey));
      if(BigB_GCD.equals(BigInteger.ONE))
      {
        break;
      }
      pubkey++;
    }
    BigInteger bigB_pubkey=new BigInteger(""+pubkey);
    BigInteger bigB_prvkey=bigB_pubkey.modInverse(bigB_p_1_q_1);

    System.out.println("public key : " + bigB_pubkey+", "+bigB_n);
    System.out.println("private key  : " + bigB_prvkey+", "+bigB_n);
  }
}
