import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VigenereChiffre extends FilterInputStream{

    private char[] key;

    protected VigenereChiffre(InputStream in, String key) {
        super(in);
        char[] keyArray = key.toCharArray();
        
        this.key = keyArray;
    }


    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int length = super.read(b, off, len);

        for (int i = 0; i < len; i++) {
            if(!(b[off+i] >= 'A' && b[off+i] <= 'Z' ))
                continue;
            byte c = (byte)(((b[off+i] - 'A')  + (key[i%key.length] - 'A') ) % 26);
            
            b[i+off] = (byte)(c + 'A');
        }

        return length;
    }
    
}
