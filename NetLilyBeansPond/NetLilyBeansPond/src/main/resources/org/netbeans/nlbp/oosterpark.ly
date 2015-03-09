\header {
  title = \markup { \italic "Oktober in het Oosterpark" }
  composer = "Mientje Deurloo"
}

\paper {
  indent = #0
}

\score {
  \new PianoStaff = "pianostaff" <<
    \new Staff = "right" \relative c'' {
      \clef "treble"
      \time 3/4
      \tempo "Mournful" 4 = 160
       g4 d'4 c4 
       ges2 aes4 
       g4 d'4 c4
       f2 ges4 g4. ges8 
       g8 d8 f4. e8 f8 c8 ees8
       d8 ees4. des8 des8 c8 e2 f4 c'4 bes4 
       e,2 g4 f4 c4 aes4 es'2 d4 des4 bes'4 aes4 d,4 
       bes'4 aes4 es4 c'4 bes8 aes8 b2 f4 
       es2 g4 ges4 aes2 es2 g4 ges4 d2
       des4 c2 b4 es2 bes2 a4 aes2
       r4
       g4 d'4 c4 
       ges2 aes4 
       g4 d'4 c4
       f2 ges4 g4. ges8 
       g8 d8 f4. e8 f8 c8 ees8
       d8 ees4. des8 des8 c8 e2 f4 c'4 bes4 
       e,2 g4 f4 c4 aes4 es'2 d4 des4 bes4 des4 d2. e2 c4 f2.
    }
    \addlyrics {
      Rose, when you fade,
      your pet als re main
      and drift a long the lake
      
    } 
  >>
  \layout { }
  \midi { }
}