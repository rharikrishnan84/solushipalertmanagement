package com.meritconinc.shiplinx.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.WebUtil;

public class PDFRenderer 
{
	
	 public static void main( String args[] ) 
	 {
		    if( 2<= args.length ) {
		      try {
		        int input_pdf_ii= 0;
		        String outFile= args[ args.length-1 ];
		        Document document= null;
		        PdfCopy writer= null;

		        while( input_pdf_ii < args.length- 1 ) {
		          // we create a reader for a certain document
		          PdfReader reader= new PdfReader( args[input_pdf_ii] );
		          reader.consolidateNamedDestinations();

		          // we retrieve the total number of pages
		          int num_pages= reader.getNumberOfPages();
		          //System.out.println( "There are "+ num_pages+ 		                              " pages in "+ args[input_pdf_ii] );
		                    
		          if( input_pdf_ii== 0 ) {
		            // step 1: creation of a document-object
		            document= new Document( reader.getPageSizeWithRotation(1) );
 
		            // step 2: we create a writer that listens to the document
		            writer= new PdfCopy( document, new FileOutputStream(outFile) );

		            // step 3: we open the document
		            document.open();
		          }

		          // step 4: we add content
		          PdfImportedPage page;
		          for( int ii= 0; ii< num_pages; ) {
		            ++ii;
		            page= writer.getImportedPage( reader, ii );
		            writer.addPage( page );
		            //System.out.println( "Processed page "+ ii );
		          }

		          PRAcroForm form= reader.getAcroForm();
		          if( form!= null ) {
		            writer.copyAcroForm( reader );
		          }

		          ++input_pdf_ii;
		        }

		        // step 5: we close the document
		        document.close();
		      }
		      catch( Exception ee ) {
		        ee.printStackTrace();
		      }
		    }
		    else { // input error
		      System.err.println("arguments: file1 [file2 ...] destfile");
		    }
		  }
	 
	 public void concatPDF(  ArrayList srcList, String dest ) 
	 {
		 if( srcList != null && dest != null ) 
		 {
			 try 
			 {
				 String outFile = dest;
				 Document document= null;
				 PdfCopy writer= null;

				 for (int i=0; i<srcList.size(); i++)
				 {
					 String src = (String) srcList.get(i);
					 
					 // we create a reader for a certain document
					 PdfReader reader= new PdfReader( src );
					 reader.consolidateNamedDestinations();

					 // we retrieve the total number of pages
					 int num_pages= reader.getNumberOfPages();
					 //System.out.println( "There are "+ num_pages + " pages in "+ src );
	                    
					 if( i==0 ) 
					 {
						 // step 1: creation of a document-object
						 document= new Document( reader.getPageSizeWithRotation(1) );
	
						 // step 2: we create a writer that listens to the document
						 writer= new PdfCopy( document, new FileOutputStream(outFile) );
	
						 // step 3: we open the document
						 document.open();
			         }

					 // step 4: we add content
			         PdfImportedPage page;
			         for( int ii= 0; ii< num_pages; ) 
			         {
			        	 ++ii;
			        	 page= writer.getImportedPage( reader, ii );
			        	 writer.addPage( page );
			        	 //System.out.println( "Processed page "+ ii );
			         }

			         PRAcroForm form= reader.getAcroForm();
			         if( form!= null ) 
			         {
			            writer.copyAcroForm( reader );
			         }
				 }
		         
		         // step 5: we close the document
		         document.close();
			 }
			 catch( Exception ee ) 
			 {
		        ee.printStackTrace();
			 }
		 }
		 else 
		 { // input error
			 System.err.println("arguments: file1 [file2 ...] destfile");
		 }
	 }
	 
	 public void concatPDF(  ArrayList srcList, OutputStream outStream ) 
	 {
		 if( srcList != null ) 
		 {
			 try 
			 {
				 Document document= null;
				 PdfCopy writer= null;

				 for (int i=0; i<srcList.size(); i++)
				 {
					 String src = (String) srcList.get(i);
					 
					 // we create a reader for a certain document
					 PdfReader reader= new PdfReader( src );
					 reader.consolidateNamedDestinations();

					 // we retrieve the total number of pages
					 int num_pages= reader.getNumberOfPages();
					 //System.out.println( "There are "+ num_pages + " pages in "+ src );
	                    
					 if( i==0 ) 
					 {
						 // step 1: creation of a document-object
						 document= new Document( reader.getPageSizeWithRotation(1) );
	
						 // step 2: we create a writer that listens to the document
						 writer= new PdfCopy( document, outStream );
	
						 // step 3: we open the document
						 document.open();
			         }

					 // step 4: we add content
			         PdfImportedPage page;
			         for( int ii= 0; ii< num_pages; ) 
			         {
			        	 ++ii;
			        	 page= writer.getImportedPage( reader, ii );
			        	 writer.addPage( page );
			        	 //System.out.println( "Processed page "+ ii );
			         }

			         PRAcroForm form= reader.getAcroForm();
			         if( form!= null ) 
			         {
			            writer.copyAcroForm( reader );
			         }
				 }
		         
		         // step 5: we close the document
		         document.close();
			 }
			 catch( Exception ee ) 
			 {
		        ee.printStackTrace();
			 }
		 }
		 else 
		 { // input error
			 System.err.println("arguments: file1 [file2 ...] destfile");
		 }
	 }
	 
	 
	 	public void deleteFiles(ArrayList srcList)
	 	{
	 		if (srcList != null)
	 		{
	 			for (int i=0; i<srcList.size(); i++)
	 			{
	 				String s = (String) srcList.get(i);
	 				deleteFile( s );
	 			}
	 		}
	 	}
	 	
		public void deleteFile(String fileName)
		{
			File dest = new File( fileName );
			dest.delete();
		}
		public String getUniqueTempPDFFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH") + File.separator + "temp";
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".pdf";
		}	 
}
