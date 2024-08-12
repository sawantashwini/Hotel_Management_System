	function onlyNumbers(evt) {
    var e = event || evt; // For trans-browser compatibility
    var charCode = e.which || e.keyCode;

    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
    	
    	 	
    

function NumToWord(inputNumber, outputControl) {
    var str = new String(inputNumber);
    var splt = str.split("");
    var rev = splt.reverse();
    var once = ['Zero', ' One', ' Two', ' Three', ' Four', ' Five', ' Six', ' Seven', ' Eight', ' Nine'];
    var twos = ['Ten', ' Eleven', ' Twelve', ' Thirteen', ' Fourteen', ' Fifteen', ' Sixteen', ' Seventeen', ' Eighteen', ' Nineteen'];
    var tens = ['', 'Ten', ' Twenty', ' Thirty', ' Forty', ' Fifty', ' Sixty', ' Seventy', ' Eighty', ' Ninety'];

    numLength = rev.length;
    var word = new Array();
    var j = 0;

    for (i = 0; i < numLength; i++) {
        switch (i) {

            case 0:
                if ((rev[i] == 0) || (rev[i + 1] == 1)) {
                    word[j] = '';
                }
                else {
                    word[j] = '' + once[rev[i]];
                }
                word[j] = word[j];
                break;

            case 1:
                aboveTens();
                break;

            case 2:
                if (rev[i] == 0) {
                    word[j] = '';
                }
                else if ((rev[i - 1] == 0) || (rev[i - 2] == 0)) {
                    word[j] = once[rev[i]] + " Hundred ";
                }
                else {
                    word[j] = once[rev[i]] + " Hundred and";
                }
                break;

            case 3:
                if (rev[i] == 0 || rev[i + 1] == 1) {
                    word[j] = '';
                }
                else {
                    word[j] = once[rev[i]];
                }
                if ((rev[i + 1] != 0) || (rev[i] > 0)) {
                    word[j] = word[j] + " Thousand";
                }
                break;

                
            case 4:
                aboveTens();
                break;

            case 5:
                if ((rev[i] == 0) || (rev[i + 1] == 1)) {
                    word[j] = '';
                }
                else {
                    word[j] = once[rev[i]];
                }
                if (rev[i + 1] !== '0' || rev[i] > '0') {
                    word[j] = word[j] + " Lakh";
                }
                 
                break;

            case 6:
                aboveTens();
                break;

            case 7:
                if ((rev[i] == 0) || (rev[i + 1] == 1)) {
                    word[j] = '';
                }
                else {
                    word[j] = once[rev[i]];
                }
                if (rev[i + 1] !== '0' || rev[i] > '0') {
                    word[j] = word[j] + " Crore";
                }                
                break;

            case 8:
                aboveTens();
                break;

            //            This is optional. 

            //            case 9:
            //                if ((rev[i] == 0) || (rev[i + 1] == 1)) {
            //                    word[j] = '';
            //                }
            //                else {
            //                    word[j] = once[rev[i]];
            //                }
            //                if (rev[i + 1] !== '0' || rev[i] > '0') {
            //                    word[j] = word[j] + " Arab";
            //                }
            //                break;

            //            case 10:
            //                aboveTens();
            //                break;

            default: break;
        }
        j++;
    }

    function aboveTens() {
        if (rev[i] == 0) { word[j] = ''; }
        else if (rev[i] == 1) { word[j] = twos[rev[i - 1]]; }
        else { word[j] = tens[rev[i]]; }
    }

    word.reverse();
  
    var finalOutput = '';
    for (i = 0; i < numLength; i++) {
        finalOutput = finalOutput + word[i];
    }
    
    document.getElementById(outputControl).innerHTML = finalOutput;
    
}




/*// Convert numbers to words
// copyright 25th July 2006, by Stephen Chapman http://javascript.about.com
// permission to use this Javascript on your web page is granted
// provided that all of the code (including this copyright notice) is
// used exactly as shown (you can change the numbering system if you wish)

// American Numbering System
var th = [ '', 'Thousand', 'Million', 'Billion', 'Trillion' ];
// uncomment this line for English Number System
// var th = ['','thousand','million', 'milliard','billion'];

var dg = [ 'Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven',
		'Eight', 'Nine' ];
var tn = [ 'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen',
		'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen' ];
var tw = [ 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty',
		'Ninety' ];
function toWords(s)
{
	s = s.toString();
	s = s.replace(/[\, ]/g, '');
	if (s != String(parseFloat(s)))
		return 'not a number';
	var x = s.indexOf('.');
	if (x == -1)
		x = s.length;
	if (x > 15)
		return 'too big';
	var n = s.split('');
	var str = '';
	var sk = 0;
	
	for ( var i = 0; i < x; i++)
	{
		if ((x - i) % 3 == 2)
		{
			if (n[i] == '1')
			{
				str += tn[Number(n[i + 1])] + ' ';
				i++;
				sk = 1;
			}
			else
				if (n[i] != 0)
				{
					str += tw[n[i] - 2] + ' ';
					sk = 1;
				}
		}
		else
			if (n[i] != 0)
			{
				str += dg[n[i]] + ' ';
				if ((x - i) % 3 == 0)
					str += 'Hundred ';
				sk = 1;
			}
		if ((x - i) % 3 == 1)
		{
			if (sk)
				str += th[(x - i - 1) / 4] + ' ';
			sk = 0;
		}
	}
	if (x != s.length)
	{
		var y = s.length;
		str += 'Point ';
		for ( var i = x + 1; i < y; i++)
			str += dg[n[i]] + ' ';
	}
	return str.replace(/\s+/g, ' ');
}// JavaScript Document
*/