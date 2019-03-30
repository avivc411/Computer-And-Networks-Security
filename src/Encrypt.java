public class Encrypt {
    public byte[] encrypt(byte[] m, byte[] keys){
        byte[] ans=new byte[m.length];
        byte[][] k=Main.GetKeys(keys);
        int size=m.length/16, counter=0;
        byte[] tmpMessage;
        while(size>1) {
            tmpMessage=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpMessage[j]=m[i];
            counter+=16;
            byte[] matrix = ShiftRows(tmpMessage), c1, c2, c3;
            c1 = AddRoundKey(matrix, k[0]);
            matrix = ShiftRows(c1);
            c2 = AddRoundKey(matrix, k[1]);
            matrix = ShiftRows(c2);
            c3=AddRoundKey(matrix, k[2]);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=c3[j];
            }
            size--;
        }
        return ans;
    }

    private byte[] ShiftRows(byte[] m){
        byte[][] matrix=new byte[4][4];
        byte [] ans=new byte[16];
        int index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                matrix[i][j]=m[index];
        for(int i=0; i<4; i++)
            for(int j=i; j>0; j--)
                swap(matrix[i]);
        index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                ans[index]=matrix[i][j];
        return ans;
    }

    private void swap(byte[] array){
        byte tmp=array[0];
        for(int i=0; i<3; i++){
            array[i]=array[i+1];
        }
        array[3]=tmp;
    }

    public static void main(String[] args) {
        int[] m={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int[][] ans=new int[4][4];
        int index=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<4; i++, index++)
                ans[i][j]=m[index];

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(ans[i][j] + " ");
            System.out.println();
        }


        byte[] array_1 = new byte[] { 3, 0, 3, 9, 0, 1 };
        for(byte[] a : Main.GetKeys(array_1)) {
            for (byte b : a)
                System.out.print(b);
            System.out.println();
        }
    }

    private static byte[] AddRoundKey(byte[] m, byte[] key){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i] = (byte)(0xff & (m[i] ^ key[i]));
        return ans;
    }
}
