FROM node:12.15.0

RUN mkdir -p /usr/src/sportsstore

COPY dist/SportsStore /usr/src/sportsstore/dist/SportsStore

COPY deploy-server.js /usr/src/sportsstore/server.js
COPY deploy-package.json /usr/src/sportsstore/package.json

WORKDIR /usr/src/sportsstore

RUN npm install

EXPOSE 3000

CMD ["node", "server.js"]
