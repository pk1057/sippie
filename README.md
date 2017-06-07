# sippie
Forked from sourceforge projekt: https://sourceforge.net/p/sippie/

Changed pcap api to pcap4j

Curently hardcoded sip ports, only udp scan, follows SIP tag insted of call-id

Feel free to improve, it currently works for me as is and will be updated if i found a problem.

If you want to build with maven, you have to run at first <b>mvn initialize</b> due to a special jedit-textArea.jar !

For deployment use <b>mvn package</b>. 
Dont forget to include the surrounding files out of the resources directory.
