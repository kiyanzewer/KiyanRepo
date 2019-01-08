evenOdd str number = if even number then str else reverse str
bous xs = [if even y then xs !! y else reverse (xs !! y) | y <- [0 ..length xs - 1]