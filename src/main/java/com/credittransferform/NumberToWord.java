package com.credittransferform;

public class NumberToWord {
	private static String unity[] = {"", " jeden", " dwa", " trzy", " cztery", " piêæ", " szeœæ", " siedem", " osiem", " dziewiêæ"};
	private static String afterTen[] = {"dziesiêæ", " jedenaœcie", " dwanaœcie", " trzynaœcie", " czternaœcie", " pietnaœcie", " szesnaœcie", " siedemnaœcie", " osiemnaœcie", " dziewiêtnaœcie"};
	private static String dozens[] ={"", " dziesiêæ", " dwadzieœcia", " trzydzieœci", " czterdzieœci", " piêædziesi¹t", " szeœædziesi¹t", " siedemdziesi¹t", " osiemdziesi¹t", " dziewiêædziesi¹t"};
	private static String hundreds[] = {"", " sto", " dwieœcie", " trzysta", " czterysta", " piêæset", " szeœæset", " siedemset", " osiemset", " dziewiêæset"};
	private static String big[] = {"", " tyœ.", " mln.", " mld.", " bln.", " bld."};
	
	
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
