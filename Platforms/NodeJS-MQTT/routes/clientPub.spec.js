var app = require('../app');
var request = require('supertest');

describe('GET /:pubTopic/:pubMessage', () => {
    it('should return 200 status code', (done) => {
        request(app)
            .get('/pub/test/123')
            .query()
            .expect(200)
            .end((err, res) => {
                if (err) throw err;
                done();
            });
    });
});
