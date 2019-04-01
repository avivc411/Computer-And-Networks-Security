public class Hack {
    Decrypt decrypt=new Decrypt();
    Encrypt encrypt=new Encrypt();
    public byte[] hack(byte[] m, byte[] c){
        byte[] ans=new byte[48], k1, k2, k3;
        k3=FirstIteration(m, c);

        return ans;
    }

    private byte[] FirstIteration(byte[] m, byte[] c){
        byte[] k1=GenerateKey(), k2=GenerateKey();
        return null;
    }

    private byte[] SecondIteration(){
        return null;
    }

    private byte[] ThirdIteration(){
        return null;
    }

    private byte[] GenerateKey(){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i]=(byte)(Math.random()*127);
        return ans;
    }
}
