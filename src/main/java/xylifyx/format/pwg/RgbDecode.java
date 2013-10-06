/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

/**
 *
 * @author emartino
 */
public class RgbDecode {
    public void decode(int width, byte[] rasterLine) {
        
    }
    
    static void
convert_rgb(
    cups_page_header2_t *header,	// I - Raster header */
    uchar               *line,		// I - Raster line
    uchar               *colors,	// O - Original pixels
    uchar               *pixels)	// O - RGB pixels
{
  int	x,				// X position in line
	w,				// Width of line
	val;				// Pixel value
  uchar	*rptr,				// Red pointer
	*gptr,				// Green pointer
	*bptr,				// Blue pointer
	bit;				// Current bit


  w = header->cupsWidth;

  if (header->cupsColorOrder == CUPS_ORDER_CHUNKED)
  {
    // Chunky
    switch (header->cupsBitsPerColor)
    {
      case 1 :
	  memset(pixels, 0, w * 3);

          for (x = w; x > 0; x -= 2, pixels += 6)
	  {
	    bit = *line++;
	    *colors++ = bit >> 4;

	    if (bit & 0x40)
	      pixels[0] = 255;
	    if (bit & 0x20)
	      pixels[1] = 255;
	    if (bit & 0x10)
	      pixels[2] = 255;

	    if (x > 1)
	    {
	      *colors++ = bit & 0x0f;

	      if (bit & 0x04)
	        pixels[3] = 255;
	      if (bit & 0x02)
	        pixels[4] = 255;
	      if (bit & 0x01)
	        pixels[5] = 255;
	    }
          }
          break;
      case 2 :
          for (x = w; x > 0; x --)
	  {
	    *colors++ = bit = *line++;

	    *pixels++ = 85 * ((bit & 0x30) >> 4);
	    *pixels++ = 85 * ((bit & 0x0c) >> 2);
	    *pixels++ = 85 * (bit & 0x03);
          }
          break;
      case 4 :
	  memset(pixels, 0, w * 3);

          for (x = w; x > 0; x --, pixels += 3)
	  {
	    bit = *line++;
	    *colors++ = bit;

	    if (bit & 0x0f)
	      pixels[0] += 17 * (bit & 0x0f);

	    bit = *line++;
	    *colors++ = bit;

	    if (bit & 0xf0)
	      pixels[1] += 17 * ((bit & 0xf0) >> 4);
	    if (bit & 0x0f)
	      pixels[2] += 17 * (bit & 0x0f);
          }
          break;
      case 8 :
	  memcpy(colors, line, w * 3);
          memcpy(pixels, line, w * 3);
          break;
      case 16 :
	  if (endian_offset)
	    *colors++ = *line++;

          for (x = w; x > 0; x --)
	  {
	    *colors++ = *pixels++ = *line++;
	    *colors++ = *line++;

	    *colors++ = *pixels++ = *line++;
	    *colors++ = *line++;

	    *colors++ = *pixels++ = *line++;
	    if (!endian_offset || x > 1)
	      *colors++ = *line++;
          }
          break;
    }
  }
  else
  {
    // Banded
    int bytespercolor = (header->cupsBitsPerColor * header->cupsWidth + 7) / 8;


    rptr = line;
    gptr = line + bytespercolor;
    bptr = line + 2 * bytespercolor;

    switch (header->cupsBitsPerColor)
    {
      case 1 :
	  memset(pixels, 0, w * 3);

          for (x = w, bit = 0x80; x > 0; x --, pixels += 3)
	  {
	    if (*rptr & bit)
	    {
	      *colors++ = 1;
	      pixels[0] = 255;
	    }
	    else
	      colors ++;

	    if (*gptr & bit)
	    {
	      *colors++ = 1;
	      pixels[1] = 255;
	    }
	    else
	      colors ++;

	    if (*bptr & bit)
	    {
	      *colors++ = 1;
	      pixels[2] = 255;
	    }
	    else
	      colors ++;

            if (bit > 1)
	      bit >>= 1;
	    else
	    {
	      bit = 0x80;
	      rptr ++;
	      gptr ++;
	      bptr ++;
	    }
          }
          break;
      case 2 :
          for (x = 0; x < w; x ++)
	  {
	    switch (x & 3)
	    {
	      case 0 :
	          *colors++ = val = (*rptr & 0xc0) >> 6;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*gptr & 0xc0) >> 6;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*bptr & 0xc0) >> 6;
		  *pixels++ = 85 * val;
                  break;
	      case 1 :
	          *colors++ = val = (*rptr & 0x30) >> 4;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*gptr & 0x30) >> 4;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*bptr & 0x30) >> 4;
		  *pixels++ = 85 * val;
                  break;
	      case 2 :
	          *colors++ = val = (*rptr & 0x0c) >> 2;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*gptr & 0x0c) >> 2;
		  *pixels++ = 85 * val;
	          *colors++ = val = (*bptr & 0x0c) >> 2;
		  *pixels++ = 85 * val;
                  break;
	      case 3 :
	          *colors++ = val = *rptr & 0x03;
		  *pixels++ = 85 * val;
	          *colors++ = val = *gptr & 0x03;
		  *pixels++ = 85 * val;
	          *colors++ = val = *bptr & 0x03;
		  *pixels++ = 85 * val;

		  rptr ++;
		  gptr ++;
		  bptr ++;
                  break;
	    }
          }
          break;
      case 4 :
          for (x = 0; x < w; x ++)
	  {
	    switch (x & 1)
	    {
	      case 0 :
	          *colors++ = val = (*rptr & 0xf0) >> 4;
		  *pixels++ = 17 * val;
	          *colors++ = val = (*gptr & 0xf0) >> 4;
		  *pixels++ = 17 * val;
	          *colors++ = val = (*bptr & 0xf0) >> 4;
		  *pixels++ = 17 * val;
                  break;
	      case 1 :
	          *colors++ = val = *rptr & 0x0f;
		  *pixels++ = 17 * val;
	          *colors++ = val = *gptr & 0x0f;
		  *pixels++ = 17 * val;
	          *colors++ = val = *bptr & 0x0f;
		  *pixels++ = 17 * val;

		  rptr ++;
		  gptr ++;
		  bptr ++;
                  break;
	    }
          }
          break;
      case 8 :
          for (x = w; x > 0; x --)
	  {
	    *colors++ = *pixels++ = *rptr++;
	    *colors++ = *pixels++ = *gptr++;
	    *colors++ = *pixels++ = *bptr++;
          }
          break;
      case 16 :
          if (endian_offset)
	  {
            for (x = w; x > 0; x --)
	    {
	      *colors++ = *rptr++;
	      *colors++ = *pixels++ = *rptr++;
	      *colors++ = *gptr++;
	      *colors++ = *pixels++ = *gptr++;
	      *colors++ = *bptr++;
	      *colors++ = *pixels++ = *bptr++;
            }
	  }
	  else
	  {
            for (x = w; x > 0; x --)
	    {
	      *colors++ = *pixels++ = *rptr++;
	      *colors++ = *rptr++;
	      *colors++ = *pixels++ = *gptr++;
	      *colors++ = *gptr++;
	      *colors++ = *pixels++ = *bptr++;
	      *colors++ = *bptr++;
            }
	  }
          break;
    }
  }
}
}
