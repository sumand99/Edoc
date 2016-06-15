package com.infogain.edoc.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;

import fr.opensagres.xdocreport.converter.MimeMapping;
import fr.opensagres.xdocreport.converter.MimeMappingConstants;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.converter.internal.AbstractConverterNoEntriesSupport;
import fr.opensagres.xdocreport.core.logging.LogUtils;
@Component
public class Converter
    extends AbstractConverterNoEntriesSupport
{

    private static final Converter INSTANCE = new Converter();

    /**ss
     * Logger for this class
     */
    private static final Logger LOGGER = LogUtils.getLogger( Converter.class.getName() );

    public static Converter getInstance()
    {
        return INSTANCE;
    }

    @Override
	public void convert( InputStream in, OutputStream out, Options options )
        throws XDocConverterException
    {

        try
        {
            
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load( in );            
            
            org.docx4j.convert.out.pdf.PdfConversion c
            
            = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion( wordMLPackage );
             //= new org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);

           // ( (org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c ).setSaveFO( new java.io.File( inputfilepath + ".fo" ) );            
            c.output( out, toPdfSettings(options) );

        }
        catch ( Exception e )
        {
            LOGGER.severe( e.getMessage() );
            throw new XDocConverterException( e );
        }
    }

    public PdfSettings toPdfSettings( Options options )
    {
        if ( options == null )
        {
            return null;
        }
        Object value = options.getSubOptions( PdfSettings.class );
        if ( value instanceof PdfSettings )
        {
            return (PdfSettings) value;
        }
       return new PdfSettings();
    }

    @Override
	public MimeMapping getMimeMapping()
    {

        return MimeMappingConstants.PDF_MIME_MAPPING;
    }
}
