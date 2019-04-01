public class Encrypt {
    public byte[] encrypt(byte[] m, byte[] keys){
        byte[] ans=new byte[m.length], tmpMessage;
        byte[][] k=Main.GetKeys(keys);
        int size=m.length/16, counter=0;
        while(size>=1) {
            tmpMessage=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpMessage[j]=m[i];
            byte[] matrix, c1, c2, c3;
            matrix = ShiftRows(tmpMessage);
            c1 = AddRoundKey(matrix, k[0]);
            matrix = ShiftRows(c1);
            c2 = AddRoundKey(matrix, k[1]);
            matrix = ShiftRows(c2);
            c3=AddRoundKey(matrix, k[2]);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=c3[j];
            }
            size--;
            counter+=16;
        }
        return ans;
    }

    public byte[] oneIteration(byte[] m, byte[] k){
        byte[] ans=new byte[m.length], tmpMessage, c, matrix;
        int size=m.length/16, counter=0;
        while(size>=1) {
            tmpMessage=new byte[16];
            for(int i=counter, j=0; i<counter+16 ;i++, j++)
                tmpMessage[j]=m[i];
            matrix = ShiftRows(tmpMessage);
            c=AddRoundKey(matrix, k);
            for(int i=counter, j=0; i<counter+16 ;i++, j++){
                ans[i]=c[j];
            }
            counter+=16;
            size--;
        }
        return ans;
    }

    public byte[] ShiftRows(byte[] m){
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

    public void swap(byte[] array){
        byte tmp=array[0];
        for(int i=0; i<3; i++){
            array[i]=array[i+1];
        }
        array[3]=tmp;
    }

    public static void main(String[] args) {
        String str="-d –k d:\\documents\\users\\saarmo\\Downloads\\keys_long -i d:\\documents\\users\\saarmo\\Downloads\\cipher_long -o d:\\documents\\users\\saarmo\\Downloads\\m_long",
        hstr="-b –m d:\\documents\\users\\saarmo\\Downloads\\message_long –c d:\\documents\\users\\saarmo\\Downloads\\cipher_long -o d:\\documents\\users\\saarmo\\Downloads\\keys_long";
        String[] arg=new String[str.split(" ").length];
        for(int i=0; i<arg.length; i++)
            arg[i]=str.split(" ")[i];
        Main.main(arg);
    }

    public static byte[] AddRoundKey(byte[] m, byte[] key){
        byte[] ans=new byte[16];
        for(int i=0; i<16; i++)
            ans[i] = (byte)(0xff & (m[i] ^ key[i]));
        return ans;
    }
}
