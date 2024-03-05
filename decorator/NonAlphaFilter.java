import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NonAlphaFilter extends FilterInputStream  {

    protected NonAlphaFilter(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        while(
            c != 10 &&  // no CR
            !(c >= 'a' && c <= 'z') && // no a-z 
            !(c >= 'A' && c <= 'Z') // no A-Z
        )   
        c = super.read();

        return c;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        byte[] _b = new byte[b.length];
        int _length = super.read(_b, 0, len);

        int k = 0;
        for (int i = 0; i < _length; i++) {
            byte c = _b[i];
            if(
                c != 10 && // no CR
                !(c >= 'a' && c <= 'z') && // no a-z
                !(c >= 'A' && c <= 'Z') // no A-Z
            ) continue;

            b[off+k] = c;
            k++;
        }

        return k;
    }
    
}
