Tre-fyrir-lokud-bil
===================
Tölvunarfræði, 2. ár, Tölvugrafík, Háskóli Íslands 2013

Í þessu verkefni á að útfæra tré fyrir lokuð bil. Leyﬁleg forritunarmál eru C, C++, Java og
Python. Skriﬁð fastayrðingu gagna fyrir klasann og notkun/fyrir/eftir lýsingu fyrir öll föll og
aðferðir.
Skriﬁð einnig forrit sem notar tré fyrir lokuð bil, les inn bil af stdin til að setja inn, takur út
eða spyr um gildi í gagnagrindinni.
Lýsingin á inntakinu er eftirfarandi:
- Hver skipun eða fyrirspurn er í einni línu sem endar á ’\n’
- Hver lína er á forminu [’+’,’?i’, ’?o’, ’?p’, ’-’] ... þar sem ... er ein eða ﬂeiri heiltölur (ekki neikvæðar).
- ’+ a b’ táknar að setja inn lokaða bilið [a; b] í gagnagrindina
- ’-’ a b táknar að taka lokaða bilið bilið [a; b] úr gagnagrindinni ef það kemur fyrir (annars gerist ekkert)
- ’?o a b’ táknar að ﬁnna öll þau bil sem skarast við bilið [a; b]
- ’?i a b’ táknar að ﬁnna öll bil sem innihalda bilið [a; b] þ.e. bil [c; d] þar sem c <= a og b <= d.
- ’?p a’ táknar að ﬁnna öll bil sem innihalda punktinn a.

Fyrir allar ? fyrirspurnir þarf að skrifa öll bilin sem uppfylla þessi skilyrði út í eina línu.
Bilin verða að vera röðuð eftir vinstra endapunkti fyrst og svo hægri endapunkti. Úttakið verður
að vera á forminu "[a, b]", t.d "[0, 10]" og bilin verða að vera aðskilin með bili á milli
sín. Ef ekkert bil uppfyllir þessi skilyrði á að prenta út "[]".
Dæmi: Ef inntakið er
+ 1 2
?o 2 3
+ 2 3
+ 0 4
?i 2 3
- 2 3
?p 2
?p 5

á úttakið að vera
[1, 2]
[0, 4] [2, 3]
[0, 4] [1, 2]


## Skil
Skilið forritskóða fyrir gagnagrindina og fyrir forritið sem notar hana. Einnig þarf að skila
hverjum þeim skrám sem þurfa að vera til staðar til að geta þýtt kóðann (t.d. Makeﬁle fyrir
C og C++, ant eða engu fyrir java og Python). Skriﬁð stutta lýsingu á útfærslunni, þ.e.
hvernig gagnagrindin lítur út, skilið einnig forritskóða fyrir gagnagrindina sem uppsettu pdf
skjali.
Einkunn verður 60% fyrir kóðann, og 40% fyrir prufuforritið. Athugið að dregið verður frá fyrir forrit sem ráða ekki við inntakið.

Einkunn: 9.5
