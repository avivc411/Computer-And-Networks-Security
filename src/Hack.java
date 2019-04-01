public class Hack {
    Encrypt encrypt=new Encrypt();

    public byte[] hack(byte[] m, byte[] c){
        byte[] ans=new byte[48], k1=GenerateKey(), k2=GenerateKey(), k3, tmpC;
        /*k3=FirstIteration(m, c);
        tmpC=decrypt.oneIteration(c, k3);
        k2=SecondIteration(m, tmpC);
        tmpC=decrypt.oneIteration(tmpC, k2);
        k1=ThirdIteration(m, tmpC);*/
        k3=encrypt.oneIteration(encrypt.oneIteration(encrypt.oneIteration(m, k1), k2), c);
        for(int i=0; i<16; i++)
            ans[i]=k1[i];
        for(int i=16, j=0; j<16; j++, i++)
            ans[i]=k2[j];
        for(int i=32, j=0; j<16; j++, i++)
            ans[i]=k3[j];
        return ans;
    }

    private byte[] FirstIteration(byte[] m, byte[] c){
        return encrypt.oneIteration(encrypt.oneIteration(encrypt.oneIteration(m, GenerateKey()), GenerateKey()), c);
    }

    private byte[] SecondIteration(byte[] m, byte[] c){
        return encrypt.oneIteration(encrypt.oneIteration(m, GenerateKey()), c);
    }

    private byte[] ThirdIteration(byte[] m, byte[] c){
        return encrypt.oneIteration(m, c);
    }

    private byte[] GenerateKey(){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i]=(byte)(Math.random()*127);
        return ans;
    }
}
