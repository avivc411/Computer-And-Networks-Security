import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if(args.length!=7)
            return;
        String keys, output, input;
        byte[] message, cipher, k;
        keys=args[2];
        input=args[4];
        output=args[6];
        if(args[0].equals("-e") || args[0].equals("-d")){
            k=Read(keys);
            if(args[0].equals("-e")){
                Encrypt encryptor=new Encrypt();
                message=Read(input);
                cipher=encryptor.encrypt(message, k);
                Write(output, cipher);
            }
            else{
                Decrypt decryptor=new Decrypt();
                cipher=Read(input);
                message=decryptor.decrypt(cipher, k);
                Write(output, message);
            }
        }
        else if(args[0].equals("-b")){
            String m=keys, c=input;
            Hack hack=new Hack();
            Write(output, hack.hack(Read(m), Read(c)));
        }
    }

    private static byte[] Read(String path){
        Path fileLocation = Paths.get(path);
        try {
             return Files.readAllBytes(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void Write(String path, byte[] output){
        Path fileLocation = Paths.get(path);
        try {
            Files.write(fileLocation, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[][] GetKeys(byte[] keys){
        byte[][] ans=new byte[3][16];
        for(int j=0, i=0, k=0; j<16; j++, k++)
            ans[i][j]=keys[k];
        for(int i=1, j=0, k=16; j<16; j++, k++)
            ans[i][j]=keys[k];
        for(int i=2, j=0, k=32; j<16; j++, k++)
            ans[i][j]=keys[k];
        return ans;
    }
}
