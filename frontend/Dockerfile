FROM node:7.8.0

WORKDIR /code
ADD src /code/src
ADD public /code/public
ADD package.json /code/package.json

RUN npm install
RUN npm run build --production
RUN npm install -g serve

CMD serve -s build
