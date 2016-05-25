"""Tests for the sample application.

The application must be running before running these tests.

Note that Flask actually has support for unit tests which avoid the
above requirement; however, the tests we have written below are
framework-agnostic, and could in principle be used to test *any* API.

"""

import requests
import unittest

app_url = 'http://127.0.0.1:5000'

class AppTests(unittest.TestCase):
    def test_homepage(self):
        """Make sure the home page exists."""
        
        r = requests.get(app_url)

        if not r.ok:
            self.fail('Status code {} != 200'.format(r.status_code))

    def test_sorting_get(self):
        """Make sure the sorting page supports GET."""

        r = requests.get(app_url + '/sorting')

        if not r.ok:
            self.fail('Status code {} != 200'.format(r.status_code))

    def test_sorting_post_1(self):
        """Make sure we can sort a list of ints."""

        r = requests.post(app_url + '/sorting', data={'values': '3,2,1'})

        if not r.ok:
            self.fail('Status code {} != 200'.format(r.status_code))

        els = r.text
        els = list(map(int, els.split(',')))
        self.assertEqual(els, [1, 2, 3])


    def test_sorting_post_2(self):
        """Make sure we can sort a list of ints with spaces."""

        r = requests.post(app_url + '/sorting', data={'values': '3, 2, 1'})

        if not r.ok:
            self.fail('Status code {} != 200'.format(r.status_code))

        els = r.text
        els = list(map(int, els.split(',')))
        self.assertEqual(els, [1, 2, 3])
