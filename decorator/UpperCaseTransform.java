import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UpperCaseTransform extends FilterInputStream{

    protected UpperCaseTransform(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c >= 'a' && c <= 'z'  ? c -32 : c;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int length = super.read(b,off,len);
        for (int i = 0; i < len; i++) {
            byte c = b[off+i];
            if(!(c >= 'a' && c <= 'z'))
                continue;
            b[off+i] = (byte) (c -32);
        }
        return length;
    }
    
    
}