package com.credittransferform;

public class NumberToWord {
	private static String unity[] = {"", " jeden", " dwa", " trzy", " cztery", " pi��", " sze��", " siedem", " osiem", " dziewi��"};
	private static String afterTen[] = {"dziesi��", " jedena�cie", " dwana�cie", " trzyna�cie", " czterna�cie", " pietna�cie", " szesna�cie", " siedemna�cie", " osiemna�cie", " dziewi�tna�cie"};
	private static String dozens[] ={"", " dziesi��", " dwadzie�cia", " trzydzie�ci", " czterdzie�ci", " pi��dziesi�t", " sze��dziesi�t", " siedemdziesi�t", " osiemdziesi�t", " dziewi��dziesi�t"};
	private static String hundreds[] = {"", " sto", " dwie�cie", " trzysta", " czterysta", " pi��set", " sze��set", " siedemset", " osiemset", " dziewi��set"};
	private static String big[] = {"", " ty�.", " mln.", " mld.", " bln.", " bld."};
	
	
	public String changeNumberToWord(long number) {
		String words = " ";
		int end;
		int row = 0;
		int j = 0;
		
		if (number==0) words="zero";
		while (number>0)
		{
		end=(int)(number%10);
		number/=10;
		if ((j==0)&&(number%100!=0 || end !=0)) words = big[row] + words;
		if ((j==0)&&(number%10!=1)) words = unity[end] + words;
		if ((j==0)&&(number%10==1))
		{
			words = afterTen[end] + words;
			number/=10;
			j+=2;
			continue;
		}
		if (j==1) words = dozens[end] + words;
		if (j==2)
		{
			words = hundreds[end] + words;
			j=-1;
			row++;
		}
		j++;
		}
		
		
		return words;
	}

}
