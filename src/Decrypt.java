public class Decrypt {
    public byte[] decrypt(byte[] c, byte[] keys){
        byte[] ans=new byte[c.length], tmpCipher;
        byte[][] k=Main.GetKeys(keys);
        int size=c.length/16, counter=0;
        while(size>=1) {
            tmpCipher=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpCipher[j]=c[i];
            byte[] matrix, c1, c2, m;
            matrix=AddRoundKey(tmpCipher, k[2]);
            c2 = ShiftRows_1(matrix);
            matrix=AddRoundKey(c2, k[1]);
            c1 = ShiftRows_1(matrix);
            matrix=AddRoundKey(c1, k[0]);
            m = ShiftRows_1(matrix);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=m[j];
            }
            counter+=16;
            size--;
        }
        return ans;
    }

    public byte[] oneIteration(byte[] c, byte[] k){
        byte[] ans=new byte[c.length];
        int size=c.length/16, counter=0;
        byte[] tmpCipher;
        while(size>=1) {
            tmpCipher=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpCipher[j]=c[i];
            byte[] matrix, m;
            matrix=AddRoundKey(tmpCipher, k);
            m = ShiftRows_1(matrix);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=m[j];
            }
            counter+=16;
            size--;
        }
        return ans;
    }

    public byte[] ShiftRows_1(byte[] c){
        byte[][] matrix=new byte[4][4];
        byte [] ans=new byte[16];
        int index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                matrix[i][j]=c[index];
        for(int i=0; i<4; i++)
            for(int j=0; j<i; j++)
                swap_back(matrix[i]);
        index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                ans[index]=matrix[i][j];
        return ans;
    }

    public void swap_back(byte[] array){
        byte tmp=array[3];
        for(int i=3; i>0; i--){
            array[i]=array[i-1];
        }
        array[0]=tmp;
    }

    public static byte[] AddRoundKey(byte[] m, byte[] key){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i] = (byte)(0xff & (m[i] ^ key[i]));
        return ans;
    }
}
