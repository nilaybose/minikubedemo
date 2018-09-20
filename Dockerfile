FROM nilaybose/dimages:u1804orajre8
RUN mkdir -p /tmp/sblogs
VOLUME /tmp/sblogs
ADD ./build/libs/*.jar /usr/local/bin/
RUN echo "java -jar /usr/local/bin/nbose-minikube-demo-0.1.0.jar --server.port=9080" > /usr/local/bin/sbrun.sh && chmod 755 /usr/local/bin/sbrun.sh
EXPOSE 9080 
ENTRYPOINT "/usr/local/bin/sbrun.sh"

