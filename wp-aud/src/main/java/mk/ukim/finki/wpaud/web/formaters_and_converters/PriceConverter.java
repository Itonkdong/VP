package mk.ukim.finki.wpaud.web.formaters_and_converters;


import org.springframework.core.convert.converter.Converter;

public class PriceConverter implements Converter<String, Double>
{

    @Override
    public Double convert(String source)
    {
        return Double.parseDouble(source);
    }
}
