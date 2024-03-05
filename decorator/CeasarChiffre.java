import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class CeaserChiffre extends FilterInputStream{

    int _off;

    protected CeaserChiffre(InputStream in,int offset) {
        super(in);
        this._off = offset;
    }


    @Override
    public int read() throws IOException {
        int c =  super.read();
        if((c >= 'A' && c <= 'Z'))
            return ((c + this._off - 'A') % ('Z'-'A' + 1)) + 'A'; 
        if((c >= 'a' && c <= 'z'))
            return ((c + this._off - 'a') % ('z'-'a' + 1)) + 'a'; 
        return c;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int lenght = super.read(b, off, len);

        for (int i = 0; i < len; i++) {
            byte c = b[i+off]; 
            byte value;
            if((c >= 'A' && c <= 'Z'))
                value = (byte)(((c + this._off - 'A') % ('Z'-'A' + 1)) + 'A'); 
            else if((c >= 'a' && c <= 'z'))
                value = (byte)(((c + this._off - 'a') % ('z'-'a' + 1)) + 'a'); 
            else continue;
            
            b[i+off] = value;

        }

        return lenght;
    }
    
}