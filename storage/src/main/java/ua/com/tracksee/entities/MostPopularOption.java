package ua.com.tracksee.entities;

import java.math.BigInteger;

/**
 * Created by kstes_000 on 06-May-15.
 */
public class MostPopularOption {
    private String additionalOptions;
    private BigInteger count;

    public MostPopularOption(String additionalOptions, BigInteger count){
        this.setAdditionalOptions(additionalOptions);
        this.setCount(count);
    }

    public String getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(String additionalOptions) {
        this.additionalOptions = additionalOptions;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
