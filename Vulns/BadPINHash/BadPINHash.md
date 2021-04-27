# Vulnerability 1.1
This vulnerability is contained within the PIN code login, the same as vulnerability 1. This vulnerability is centred around the way a hash has been created and stored, and is an example of [insufficient cryptography].  

## Hashing
Hashing is a process used to map data to a certain value. A hash function cannot be reversed or mapped back to its original value, and therefore cannot be considered encryption, as encryption is a two way operation (i.e. a value can be encrypted and decrypted). Read more about hashing versus encryption [here].  

The PIN is passed through a hash function and then the resultant hash is stored in the file `/data/data/com.example.dvma/cache`. The same restraints still apply, as in there are 9999 possible combinations to sort through.  

The SHA-512 hash is used to create the value found in `/data/data/com.example.dvma/cache`. The implementation of this algorithm does not use a salt. [This extremely useful video] explains the SHA-512 algorithm well. Resources for programming a hash function can be found in the following:
* [Python]
* [Java]

## Task
Create a program that goes through every PIN combination and hashes it using the SHA-512 algorithm. Compare this hash to the one found in `/data/data/com.example.dvma/cache`. You can use any programming language you want as long as it has the option to create a SHA-512 hash.  

### Hint 1
Create an iterative loop to go through all 9999 combinations.

### Hint 2
Make sure the hash function is using SHA-512.

## The Fix
There are several ways to fix this vulnerability, the first being using a randomly generated salt and storing it securely.  

Here is the code to add a salt to the algorithm:  

`fun getSalt() : ByteArray {`  

`  val rnd = SecureRandom()`  
  
`  val salt = ByteArray(16)`  
  
`  rnd.nextBytes(salt)`  
  
`  return salt`  
  
`}`

The [solution can be found here].

[insufficient cryptography]: https://owasp.org/www-project-mobile-top-10/2016-risks/m5-insufficient-cryptography
[Java]: https://techexpertise.medium.com/java-cryptographic-hash-functions-a7ae28f3fa42
[Python]: https://www.geeksforgeeks.org/sha-in-python/
[This extremely useful video]: https://www.youtube.com/watch?v=DMtFhACPnTY&t=351s
[here]: https://aboutssl.org/hashing-vs-encryption/
[solution can be found here]: https://pl.kotl.in/5Fr98F7zB
