\header {
  title = \markup { \italic "Happy Birthday, Java!" }
}

\paper {
  indent = #0
}

\score {
  \new PianoStaff = "pianostaff" <<
    \new Staff = "right" \relative c'' {
      \clef "treble"
      \time 3/4
      \tempo "Quick and snappy" 4 = 160
       r2 c8 c
       a'4 g f
       g2 e4
       f r f
       g2 c,4
       a' b c
       g2 e8 e
       f4 e d
       c2. r2. \bar "|."
    }
    \addlyrics {
      Ha py birth -- day Ja va, 
      you are twen ty!
      For fun and for gain, 
      pub -- lic sta -- tic void main!
    } 
    \new Staff = "left" \relative c {
      \clef "bass"
       c4 <e g> <e g>
       f4 <a c> <a c>
       c,4 <e g> <e g>
       g,4 <b d> <b d>
       c4 <e g> <e g>
       f4 <a c> <a c>
       c,4 <e g> <e g>
       g,4 <b d> <b d>
       c4 <e g> <e g>
       c2. \bar "|."
    }
  >>
  \layout { }
  \midi { }
}