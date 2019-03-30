public class Decrypt {
    public byte[] decrypt(byte[] c, byte[] keys){
        byte[] ans=new byte[c.length];
        byte[][] k=Main.GetKeys(keys);
        int size=c.length/16, counter=0;
        byte[] tmpCipher;
        while(size>1) {
            tmpCipher=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpCipher[j]=c[i];
            counter+=16;
            byte[] matrix, c1, c2, m;
            matrix=AddRoundKey(c, k[2]);
            c2 = ShiftRows_1(matrix);
            matrix=AddRoundKey(c2, k[1]);
            c1 = ShiftRows_1(matrix);
            matrix=AddRoundKey(c1, k[0]);
            m = ShiftRows_1(matrix);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=m[j];
            }
            size--;
        }
        return ans;
    }

    private byte[] ShiftRows_1(byte[] c){
        byte[][] matrix=new byte[4][4];
        byte [] ans=new byte[16];
        int index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                matrix[i][j]=c[index];
        for(int i=0; i<4; i++)
            for(int j=i; j>0; j--)
                swap_back(matrix[i]);
        index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                ans[index]=matrix[i][j];
        return ans;
    }

    private void swap_back(byte[] array){
        byte tmp=array[3];
        for(int i=0; i<3; i++){
            array[i+1]=array[i];
        }
        array[0]=tmp;
    }

    private static byte[] AddRoundKey(byte[] m, byte[] key){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i] = (byte)(0xff & (m[i] ^ key[i]));
        return ans;
    }
}
