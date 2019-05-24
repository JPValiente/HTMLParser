package Parser.HTML;

import java_cup.runtime.*;
import static Parser.HTML.sym.*;
%% //separador de area

/* opciones y declaraciones de jflex */

%class Analizador_HTML
%line
%public
%cup
%cupdebug
%column

DOLAR = ["$"]
GUION_BAJO = ["_"]
GUION = ["-"]
LETRA = [a-zA-Z]
DIGITO = [0-9]
SEPARADORES = [ \n\r\t]
CUALQUIERA = [^]
SALTO_LINEA_NO = [^\n]
SALTO_LINEA= [\n]
DOSPUNTOS = [":"]
SIGNO_GATO = ["#"]
COMA = [","]
MENOR = ["<"]
APOS = ["'"]
MAYOR = [">"]
COMILLA = [\"]
MAS = ["+"]
MULTI = ["*"]
IGUAL = ["="]
MOD = ["%"]
DIVISION = ["/"]
PABIERTO = ["("]
PCERRADO = [")"]
CABIERTO = ["["]
CCERRADO = ["]"]
MENOR = ["<"]
MAYOR = [">"]
BLANKSPACE = [" "]


%{

  StringBuilder string = new StringBuilder();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  private void error(String message) {
    ErrorI.addError(new ErrorI(yyline+1,yycolumn+1,"Simbolo " + message + " no reconocido para la gramatica",MainFrame.currentFile,'a'));
  }
%}

%% // separador de areas

/* reglas lexicas */
<YYINITIAL> {

//Palabras Reservadas
{APOS} {return symbol(APOSTROFE);}
"a" {return symbol(OREF,yytext());}
"#" {return symbol(SIGNO_GATO,yytext());}
("1"|"A") {return symbol(TIPO,yytext());}
{MENOR} {return symbol(APERTURA,yytext());}
{MAYOR} {return symbol(CIERRE,yytext());}
{DIVISION} {return symbol(DIAGONAL,yytext());}
{IGUAL} {return symbol(IGUAL,yytext());}
{CABIERTO} {return symbol(CABIERTO,yytext());}
{CCERRADO} {return symbol(CCERRADO,yytext());}
"<html>" {return symbol(OHTML,yytext());}
"</html>" {return symbol(CHTML,yytext());}
"<head>" {return symbol(OHEAD,yytext());}
"</head>" {return symbol(CHEAD,yytext());}
"<title>" {return symbol(OTITLE,yytext());}
"</title>" {return symbol(CTITLE  ,yytext());}
"</body>" {return symbol(CBODY  ,yytext());}
"body" {return symbol(BODY,yytext());}
"size" {return symbol(SIZE,yytext());}
"type" {return symbol(TYPE,yytext());}
"face" {return symbol(FACE,yytext());}
"ul" {return symbol(UL,yytext());}
"ol" {return symbol(OL,yytext());}
"<li>" {return symbol(LI,yytext());}
"<p>" {return symbol(PAR,yytext());}
"p" {return symbol(PARAGRAPH,yytext());}
"text" {return symbol(TEXT,yytext());}
"link" {return symbol(LINK,yytext());}
"align" {return symbol(ALIGN,yytext());}
"width" {return symbol(WIDTH,yytext());}
"bgcolor" {return symbol(BGCOLOR,yytext());}
"color" {return symbol(COLOR,yytext());}
"font" {return symbol(FONT,yytext());}
"hr" {return symbol(HR,yytext());}
"href" {return symbol(HREF,yytext());}

"start" {return symbol(START,yytext());}

"black" {return symbol(BLACK,yytext());}
"olive" {return symbol(OLIVE,yytext());}
"teal" {return symbol(TEAL,yytext());}
"red" {return symbol(RED,yytext());}
"blue" {return symbol(BLUE,yytext());}
"maroon" {return symbol(MAROON,yytext());}
"navy" {return symbol(NAVY,yytext());}
"gray" {return symbol(GRAY,yytext());}
"lime" {return symbol(LIME,yytext());}
"fuchsia" {return symbol(FUCHSIA,yytext());}
"green" {return symbol(GREEN,yytext());}
"white" {return symbol(WHITE,yytext());}
"purple" {return symbol(PURPLE,yytext());}
"silver" {return symbol(SILVER,yytext());}
"yellow" {return symbol(YELLOW,yytext());}
"aqua" {return symbol(AQUA,yytext());}
"left" {return symbol(LEFT,yytext());}
"right" {return symbol(RIGHT,yytext());}
"center" {return symbol(CENTER,yytext());}
"justified" {return symbol(JUSTIFIED,yytext());}

"circle" {return symbol(CIRCLE,yytext());}
"square" {return symbol(SQUARE,yytext());}
"disc" {return symbol(DISC,yytext());}


"</a>" {return symbol(CREF,yytext());}
"<center>" {return symbol(OCENTER,yytext());}
"</center>" {return symbol(CCENTER,yytext());}
"<b>" {return symbol(OBOLD,yytext());}
"</b>" {return symbol(CBOLD,yytext());}
"<u>" {return symbol(OSUBRA,yytext());}
"</u>" {return symbol(CSUBRA,yytext());}
"<i>" {return symbol(OITALIC,yytext());}
"</i>" {return symbol(CITALIC,yytext());}
"<strike>" {return symbol(OSTRIKE,yytext());}
"</strike>" {return symbol(CSTRIKE,yytext());}
"<blink>" {return symbol(OBLINK,yytext());}
"</blink>" {return symbol(CBLINK,yytext());}
"<sub>" {return symbol(OSUB,yytext());}
"</sub>" {return symbol(CSUB,yytext());}
"<sup>" {return symbol(OSUP,yytext());}
"</sup>" {return symbol(CSUP,yytext());}
"<br>" {return symbol(BR,yytext());}
"<nobr>" {return symbol(ONOBR,yytext());}
"</nobr>" {return symbol(CNOBR,yytext());}
"<blockquote>" {return symbol(OBLOCKQUOTE,yytext());}
"</blockquote>" {return symbol(CBLOCKQUOTE,yytext());}
"</p>" {return symbol(CP,yytext());}
"</font>" {return symbol(CFONT,yytext());}

"</ol>" {return symbol(COL,yytext());}
"</ul>" {return symbol(CUL,yytext());}
"name" {return symbol(NAME,yytext());}

({DIGITO})+ {return symbol(NUMERO,yytext());}
({DIGITO})+{MOD} {return symbol(PORCENTAJE,yytext());}
{COMILLA} {return symbol(COMILLA,yytext());}

({DIGITO}|{LETRA})({DIGITO}|{LETRA})({DIGITO}|{LETRA})({DIGITO}|{LETRA})({DIGITO}|{LETRA})({DIGITO}|{LETRA}) {return symbol(HEXADECIMAL,yytext());}
({DIGITO}|{LETRA})+ {return symbol(TAG,yytext());}
("'")({LETRA}|{DIGITO}|{MENOR}|{MAYOR}|{DOSPUNTOS}|{SIGNO_GATO}|{COMA}|{MAS}|{MULTI}|{IGUAL}|{DIVISION}|{PABIERTO}|{PCERRADO}|{CABIERTO}|{CCERRADO}|{DOLAR}|{GUION}|{GUION_BAJO})* ("'") {return symbol(TEXTO,yytext());}


"&lt" {return symbol(LT,"<");}
"&gt" {return symbol(GT,">");}
"&amp" {return symbol(AMP,"&");}
"&aacute" {return symbol(AACUTE,"á");}
"&eacute" {return symbol(EACUTE,"é");}
"&iacute" {return symbol(IACUTE,"í");}
"&oacute" {return symbol(OACUTE,"ó");}
"&uacute" {return symbol(UACUTE,"ú");}
"&Ntilde" {return symbol(NTILDEMAY,"Ñ");}
"&ntilde" {return symbol(NTILDEMIN,"ñ");}


{SEPARADORES} {}



}
[^]                              {error(yytext());}
<<EOF>>                 { return symbol(EOF); }
