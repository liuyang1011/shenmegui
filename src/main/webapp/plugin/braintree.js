/**
 * Created by vincentfxz on 15/9/21.
 */
!function () {
    function t(e, n) {
        e instanceof t ? (this.enc = e.enc, this.pos = e.pos) : (this.enc = e, this.pos = n)
    }

    function e(t, e, n, r, i) {
        this.stream = t, this.header = e, this.length = n, this.tag = r, this.sub = i
    }

    function n(t) {
        var e, n, r = "";
        for (e = 0; e + 3 <= t.length; e += 3)n = parseInt(t.substring(e, e + 3), 16), r += ee.charAt(n >> 6) + ee.charAt(63 & n);
        for (e + 1 == t.length ? (n = parseInt(t.substring(e, e + 1), 16), r += ee.charAt(n << 2)) : e + 2 == t.length && (n = parseInt(t.substring(e, e + 2), 16), r += ee.charAt(n >> 2) + ee.charAt((3 & n) << 4)); (3 & r.length) > 0;)r += ne;
        return r
    }

    function r(t) {
        var e, n, r, i = "", o = 0;
        for (e = 0; e < t.length && t.charAt(e) != ne; ++e)r = ee.indexOf(t.charAt(e)), 0 > r || (0 == o ? (i += l(r >> 2), n = 3 & r, o = 1) : 1 == o ? (i += l(n << 2 | r >> 4), n = 15 & r, o = 2) : 2 == o ? (i += l(n), i += l(r >> 2), n = 3 & r, o = 3) : (i += l(n << 2 | r >> 4), i += l(15 & r), o = 0));
        return 1 == o && (i += l(n << 2)), i
    }

    function i(t) {
        var e, n = r(t), i = new Array;
        for (e = 0; 2 * e < n.length; ++e)i[e] = parseInt(n.substring(2 * e, 2 * e + 2), 16);
        return i
    }

    function o(t, e, n) {
        null != t && ("number" == typeof t ? this.fromNumber(t, e, n) : null == e && "string" != typeof t ? this.fromString(t, 256) : this.fromString(t, e))
    }

    function s() {
        return new o(null)
    }

    function a(t, e, n, r, i, o) {
        for (; --o >= 0;) {
            var s = e * this[t++] + n[r] + i;
            i = Math.floor(s / 67108864), n[r++] = 67108863 & s
        }
        return i
    }

    function u(t, e, n, r, i, o) {
        for (var s = 32767 & e, a = e >> 15; --o >= 0;) {
            var u = 32767 & this[t], c = this[t++] >> 15, l = a * u + c * s;
            u = s * u + ((32767 & l) << 15) + n[r] + (1073741823 & i), i = (u >>> 30) + (l >>> 15) + a * c + (i >>> 30), n[r++] = 1073741823 & u
        }
        return i
    }

    function c(t, e, n, r, i, o) {
        for (var s = 16383 & e, a = e >> 14; --o >= 0;) {
            var u = 16383 & this[t], c = this[t++] >> 14, l = a * u + c * s;
            u = s * u + ((16383 & l) << 14) + n[r] + i, i = (u >> 28) + (l >> 14) + a * c, n[r++] = 268435455 & u
        }
        return i
    }

    function l(t) {
        return ue.charAt(t)
    }

    function p(t, e) {
        var n = ce[t.charCodeAt(e)];
        return null == n ? -1 : n
    }

    function h(t) {
        for (var e = this.t - 1; e >= 0; --e)t[e] = this[e];
        t.t = this.t, t.s = this.s
    }

    function d(t) {
        this.t = 1, this.s = 0 > t ? -1 : 0, t > 0 ? this[0] = t : -1 > t ? this[0] = t + this.DV : this.t = 0
    }

    function f(t) {
        var e = s();
        return e.fromInt(t), e
    }

    function m(t, e) {
        var n;
        if (16 == e)n = 4; else if (8 == e)n = 3; else if (256 == e)n = 8; else if (2 == e)n = 1; else if (32 == e)n = 5; else {
            if (4 != e)return void this.fromRadix(t, e);
            n = 2
        }
        this.t = 0, this.s = 0;
        for (var r = t.length, i = !1, s = 0; --r >= 0;) {
            var a = 8 == n ? 255 & t[r] : p(t, r);
            0 > a ? "-" == t.charAt(r) && (i = !0) : (i = !1, 0 == s ? this[this.t++] = a : s + n > this.DB ? (this[this.t - 1] |= (a & (1 << this.DB - s) - 1) << s, this[this.t++] = a >> this.DB - s) : this[this.t - 1] |= a << s, s += n, s >= this.DB && (s -= this.DB))
        }
        8 == n && 0 != (128 & t[0]) && (this.s = -1, s > 0 && (this[this.t - 1] |= (1 << this.DB - s) - 1 << s)), this.clamp(), i && o.ZERO.subTo(this, this)
    }

    function g() {
        for (var t = this.s & this.DM; this.t > 0 && this[this.t - 1] == t;)--this.t
    }

    function y(t) {
        if (this.s < 0)return "-" + this.negate().toString(t);
        var e;
        if (16 == t)e = 4; else if (8 == t)e = 3; else if (2 == t)e = 1; else if (32 == t)e = 5; else {
            if (4 != t)return this.toRadix(t);
            e = 2
        }
        var n, r = (1 << e) - 1, i = !1, o = "", s = this.t, a = this.DB - s * this.DB % e;
        if (s-- > 0)for (a < this.DB && (n = this[s] >> a) > 0 && (i = !0, o = l(n)); s >= 0;)e > a ? (n = (this[s] & (1 << a) - 1) << e - a, n |= this[--s] >> (a += this.DB - e)) : (n = this[s] >> (a -= e) & r, 0 >= a && (a += this.DB, --s)), n > 0 && (i = !0), i && (o += l(n));
        return i ? o : "0"
    }

    function b() {
        var t = s();
        return o.ZERO.subTo(this, t), t
    }

    function v() {
        return this.s < 0 ? this.negate() : this
    }

    function _(t) {
        var e = this.s - t.s;
        if (0 != e)return e;
        var n = this.t;
        if (e = n - t.t, 0 != e)return this.s < 0 ? -e : e;
        for (; --n >= 0;)if (0 != (e = this[n] - t[n]))return e;
        return 0
    }

    function E(t) {
        var e, n = 1;
        return 0 != (e = t >>> 16) && (t = e, n += 16), 0 != (e = t >> 8) && (t = e, n += 8), 0 != (e = t >> 4) && (t = e, n += 4), 0 != (e = t >> 2) && (t = e, n += 2), 0 != (e = t >> 1) && (t = e, n += 1), n
    }

    function w() {
        return this.t <= 0 ? 0 : this.DB * (this.t - 1) + E(this[this.t - 1] ^ this.s & this.DM)
    }

    function C(t, e) {
        var n;
        for (n = this.t - 1; n >= 0; --n)e[n + t] = this[n];
        for (n = t - 1; n >= 0; --n)e[n] = 0;
        e.t = this.t + t, e.s = this.s
    }

    function A(t, e) {
        for (var n = t; n < this.t; ++n)e[n - t] = this[n];
        e.t = Math.max(this.t - t, 0), e.s = this.s
    }

    function S(t, e) {
        var n, r = t % this.DB, i = this.DB - r, o = (1 << i) - 1, s = Math.floor(t / this.DB), a = this.s << r & this.DM;
        for (n = this.t - 1; n >= 0; --n)e[n + s + 1] = this[n] >> i | a, a = (this[n] & o) << r;
        for (n = s - 1; n >= 0; --n)e[n] = 0;
        e[s] = a, e.t = this.t + s + 1, e.s = this.s, e.clamp()
    }

    function x(t, e) {
        e.s = this.s;
        var n = Math.floor(t / this.DB);
        if (n >= this.t)return void(e.t = 0);
        var r = t % this.DB, i = this.DB - r, o = (1 << r) - 1;
        e[0] = this[n] >> r;
        for (var s = n + 1; s < this.t; ++s)e[s - n - 1] |= (this[s] & o) << i, e[s - n] = this[s] >> r;
        r > 0 && (e[this.t - n - 1] |= (this.s & o) << i), e.t = this.t - n, e.clamp()
    }

    function T(t, e) {
        for (var n = 0, r = 0, i = Math.min(t.t, this.t); i > n;)r += this[n] - t[n], e[n++] = r & this.DM, r >>= this.DB;
        if (t.t < this.t) {
            for (r -= t.s; n < this.t;)r += this[n], e[n++] = r & this.DM, r >>= this.DB;
            r += this.s
        } else {
            for (r += this.s; n < t.t;)r -= t[n], e[n++] = r & this.DM, r >>= this.DB;
            r -= t.s
        }
        e.s = 0 > r ? -1 : 0, -1 > r ? e[n++] = this.DV + r : r > 0 && (e[n++] = r), e.t = n, e.clamp()
    }

    function O(t, e) {
        var n = this.abs(), r = t.abs(), i = n.t;
        for (e.t = i + r.t; --i >= 0;)e[i] = 0;
        for (i = 0; i < r.t; ++i)e[i + n.t] = n.am(0, r[i], e, i, 0, n.t);
        e.s = 0, e.clamp(), this.s != t.s && o.ZERO.subTo(e, e)
    }

    function P(t) {
        for (var e = this.abs(), n = t.t = 2 * e.t; --n >= 0;)t[n] = 0;
        for (n = 0; n < e.t - 1; ++n) {
            var r = e.am(n, e[n], t, 2 * n, 0, 1);
            (t[n + e.t] += e.am(n + 1, 2 * e[n], t, 2 * n + 1, r, e.t - n - 1)) >= e.DV && (t[n + e.t] -= e.DV, t[n + e.t + 1] = 1)
        }
        t.t > 0 && (t[t.t - 1] += e.am(n, e[n], t, 2 * n, 0, 1)), t.s = 0, t.clamp()
    }

    function N(t, e, n) {
        var r = t.abs();
        if (!(r.t <= 0)) {
            var i = this.abs();
            if (i.t < r.t)return null != e && e.fromInt(0), void(null != n && this.copyTo(n));
            null == n && (n = s());
            var a = s(), u = this.s, c = t.s, l = this.DB - E(r[r.t - 1]);
            l > 0 ? (r.lShiftTo(l, a), i.lShiftTo(l, n)) : (r.copyTo(a), i.copyTo(n));
            var p = a.t, h = a[p - 1];
            if (0 != h) {
                var d = h * (1 << this.F1) + (p > 1 ? a[p - 2] >> this.F2 : 0), f = this.FV / d, m = (1 << this.F1) / d, g = 1 << this.F2, y = n.t, b = y - p, v = null == e ? s() : e;
                for (a.dlShiftTo(b, v), n.compareTo(v) >= 0 && (n[n.t++] = 1, n.subTo(v, n)), o.ONE.dlShiftTo(p, v), v.subTo(a, a); a.t < p;)a[a.t++] = 0;
                for (; --b >= 0;) {
                    var _ = n[--y] == h ? this.DM : Math.floor(n[y] * f + (n[y - 1] + g) * m);
                    if ((n[y] += a.am(0, _, n, b, 0, p)) < _)for (a.dlShiftTo(b, v), n.subTo(v, n); n[y] < --_;)n.subTo(v, n)
                }
                null != e && (n.drShiftTo(p, e), u != c && o.ZERO.subTo(e, e)), n.t = p, n.clamp(), l > 0 && n.rShiftTo(l, n), 0 > u && o.ZERO.subTo(n, n)
            }
        }
    }

    function I(t) {
        var e = s();
        return this.abs().divRemTo(t, null, e), this.s < 0 && e.compareTo(o.ZERO) > 0 && t.subTo(e, e), e
    }

    function R(t) {
        this.m = t
    }

    function k(t) {
        return t.s < 0 || t.compareTo(this.m) >= 0 ? t.mod(this.m) : t
    }

    function D(t) {
        return t
    }

    function M(t) {
        t.divRemTo(this.m, null, t)
    }

    function U(t, e, n) {
        t.multiplyTo(e, n), this.reduce(n)
    }

    function L(t, e) {
        t.squareTo(e), this.reduce(e)
    }

    function F() {
        if (this.t < 1)return 0;
        var t = this[0];
        if (0 == (1 & t))return 0;
        var e = 3 & t;
        return e = e * (2 - (15 & t) * e) & 15, e = e * (2 - (255 & t) * e) & 255, e = e * (2 - ((65535 & t) * e & 65535)) & 65535, e = e * (2 - t * e % this.DV) % this.DV, e > 0 ? this.DV - e : -e
    }

    function j(t) {
        this.m = t, this.mp = t.invDigit(), this.mpl = 32767 & this.mp, this.mph = this.mp >> 15, this.um = (1 << t.DB - 15) - 1, this.mt2 = 2 * t.t
    }

    function B(t) {
        var e = s();
        return t.abs().dlShiftTo(this.m.t, e), e.divRemTo(this.m, null, e), t.s < 0 && e.compareTo(o.ZERO) > 0 && this.m.subTo(e, e), e
    }

    function z(t) {
        var e = s();
        return t.copyTo(e), this.reduce(e), e
    }

    function H(t) {
        for (; t.t <= this.mt2;)t[t.t++] = 0;
        for (var e = 0; e < this.m.t; ++e) {
            var n = 32767 & t[e], r = n * this.mpl + ((n * this.mph + (t[e] >> 15) * this.mpl & this.um) << 15) & t.DM;
            for (n = e + this.m.t, t[n] += this.m.am(0, r, t, e, 0, this.m.t); t[n] >= t.DV;)t[n] -= t.DV, t[++n]++
        }
        t.clamp(), t.drShiftTo(this.m.t, t), t.compareTo(this.m) >= 0 && t.subTo(this.m, t)
    }

    function V(t, e) {
        t.squareTo(e), this.reduce(e)
    }

    function Y(t, e, n) {
        t.multiplyTo(e, n), this.reduce(n)
    }

    function q() {
        return 0 == (this.t > 0 ? 1 & this[0] : this.s)
    }

    function W(t, e) {
        if (t > 4294967295 || 1 > t)return o.ONE;
        var n = s(), r = s(), i = e.convert(this), a = E(t) - 1;
        for (i.copyTo(n); --a >= 0;)if (e.sqrTo(n, r), (t & 1 << a) > 0)e.mulTo(r, i, n); else {
            var u = n;
            n = r, r = u
        }
        return e.revert(n)
    }

    function G(t, e) {
        var n;
        return n = 256 > t || e.isEven() ? new R(e) : new j(e), this.exp(t, n)
    }

    function Q(t, e) {
        return new o(t, e)
    }

    function K(t, e) {
        if (e < t.length + 11)throw new Error("Message too long for RSA");
        for (var n = new Array, r = t.length - 1; r >= 0 && e > 0;) {
            var i = t.charCodeAt(r--);
            128 > i ? n[--e] = i : i > 127 && 2048 > i ? (n[--e] = 63 & i | 128, n[--e] = i >> 6 | 192) : (n[--e] = 63 & i | 128, n[--e] = i >> 6 & 63 | 128, n[--e] = i >> 12 | 224)
        }
        n[--e] = 0;
        for (var s = 0, a = 0, u = 0; e > 2;)0 == u && (a = le.random.randomWords(1, 0)[0]), s = a >> u & 255, u = (u + 8) % 32, 0 != s && (n[--e] = s);
        return n[--e] = 2, n[--e] = 0, new o(n)
    }

    function $() {
        this.n = null, this.e = 0, this.d = null, this.p = null, this.q = null, this.dmp1 = null, this.dmq1 = null, this.coeff = null
    }

    function Z(t, e) {
        if (!(null != t && null != e && t.length > 0 && e.length > 0))throw new Error("Invalid RSA public key");
        this.n = Q(t, 16), this.e = parseInt(e, 16)
    }

    function X(t) {
        return t.modPowInt(this.e, this.n)
    }

    function J(t) {
        var e = K(t, this.n.bitLength() + 7 >> 3);
        if (null == e)return null;
        var n = this.doPublic(e);
        if (null == n)return null;
        var r = n.toString(16);
        return 0 == (1 & r.length) ? r : "0" + r
    }

    t.prototype.get = function (t) {
        if (void 0 == t && (t = this.pos++), t >= this.enc.length)throw"Requesting byte offset " + t + " on a stream of length " + this.enc.length;
        return this.enc[t]
    }, t.prototype.hexDigits = "0123456789ABCDEF", t.prototype.hexByte = function (t) {
        return this.hexDigits.charAt(t >> 4 & 15) + this.hexDigits.charAt(15 & t)
    }, t.prototype.hexDump = function (t, e) {
        for (var n = "", r = t; e > r; ++r)switch (n += this.hexByte(this.get(r)), 15 & r) {
            case 7:
                n += "  ";
                break;
            case 15:
                n += "\n";
                break;
            default:
                n += " "
        }
        return n
    }, t.prototype.parseStringISO = function (t, e) {
        for (var n = "", r = t; e > r; ++r)n += String.fromCharCode(this.get(r));
        return n
    }, t.prototype.parseStringUTF = function (t, e) {
        for (var n = "", r = 0, i = t; e > i;) {
            var r = this.get(i++);
            n += String.fromCharCode(128 > r ? r : r > 191 && 224 > r ? (31 & r) << 6 | 63 & this.get(i++) : (15 & r) << 12 | (63 & this.get(i++)) << 6 | 63 & this.get(i++))
        }
        return n
    }, t.prototype.reTime = /^((?:1[89]|2\d)?\d\d)(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])([01]\d|2[0-3])(?:([0-5]\d)(?:([0-5]\d)(?:[.,](\d{1,3}))?)?)?(Z|[-+](?:[0]\d|1[0-2])([0-5]\d)?)?$/, t.prototype.parseTime = function (t, e) {
        var n = this.parseStringISO(t, e), r = this.reTime.exec(n);
        return r ? (n = r[1] + "-" + r[2] + "-" + r[3] + " " + r[4], r[5] && (n += ":" + r[5], r[6] && (n += ":" + r[6], r[7] && (n += "." + r[7]))), r[8] && (n += " UTC", "Z" != r[8] && (n += r[8], r[9] && (n += ":" + r[9]))), n) : "Unrecognized time: " + n
    }, t.prototype.parseInteger = function (t, e) {
        var n = e - t;
        if (n > 4) {
            n <<= 3;
            var r = this.get(t);
            if (0 == r)n -= 8; else for (; 128 > r;)r <<= 1, --n;
            return "(" + n + " bit)"
        }
        for (var i = 0, o = t; e > o; ++o)i = i << 8 | this.get(o);
        return i
    }, t.prototype.parseBitString = function (t, e) {
        var n = this.get(t), r = (e - t - 1 << 3) - n, i = "(" + r + " bit)";
        if (20 >= r) {
            var o = n;
            i += " ";
            for (var s = e - 1; s > t; --s) {
                for (var a = this.get(s), u = o; 8 > u; ++u)i += a >> u & 1 ? "1" : "0";
                o = 0
            }
        }
        return i
    }, t.prototype.parseOctetString = function (t, e) {
        var n = e - t, r = "(" + n + " byte) ";
        n > 20 && (e = t + 20);
        for (var i = t; e > i; ++i)r += this.hexByte(this.get(i));
        return n > 20 && (r += String.fromCharCode(8230)), r
    }, t.prototype.parseOID = function (t, e) {
        for (var n, r = 0, i = 0, o = t; e > o; ++o) {
            var s = this.get(o);
            r = r << 7 | 127 & s, i += 7, 128 & s || (void 0 == n ? n = parseInt(r / 40) + "." + r % 40 : n += "." + (i >= 31 ? "bigint" : r), r = i = 0), n += String.fromCharCode()
        }
        return n
    }, e.prototype.typeName = function () {
        if (void 0 == this.tag)return "unknown";
        var t = this.tag >> 6, e = (this.tag >> 5 & 1, 31 & this.tag);
        switch (t) {
            case 0:
                switch (e) {
                    case 0:
                        return "EOC";
                    case 1:
                        return "BOOLEAN";
                    case 2:
                        return "INTEGER";
                    case 3:
                        return "BIT_STRING";
                    case 4:
                        return "OCTET_STRING";
                    case 5:
                        return "NULL";
                    case 6:
                        return "OBJECT_IDENTIFIER";
                    case 7:
                        return "ObjectDescriptor";
                    case 8:
                        return "EXTERNAL";
                    case 9:
                        return "REAL";
                    case 10:
                        return "ENUMERATED";
                    case 11:
                        return "EMBEDDED_PDV";
                    case 12:
                        return "UTF8String";
                    case 16:
                        return "SEQUENCE";
                    case 17:
                        return "SET";
                    case 18:
                        return "NumericString";
                    case 19:
                        return "PrintableString";
                    case 20:
                        return "TeletexString";
                    case 21:
                        return "VideotexString";
                    case 22:
                        return "IA5String";
                    case 23:
                        return "UTCTime";
                    case 24:
                        return "GeneralizedTime";
                    case 25:
                        return "GraphicString";
                    case 26:
                        return "VisibleString";
                    case 27:
                        return "GeneralString";
                    case 28:
                        return "UniversalString";
                    case 30:
                        return "BMPString";
                    default:
                        return "Universal_" + e.toString(16)
                }
            case 1:
                return "Application_" + e.toString(16);
            case 2:
                return "[" + e + "]";
            case 3:
                return "Private_" + e.toString(16)
        }
    }, e.prototype.content = function () {
        if (void 0 == this.tag)return null;
        var t = this.tag >> 6;
        if (0 != t)return null == this.sub ? null : "(" + this.sub.length + ")";
        var e = 31 & this.tag, n = this.posContent(), r = Math.abs(this.length);
        switch (e) {
            case 1:
                return 0 == this.stream.get(n) ? "false" : "true";
            case 2:
                return this.stream.parseInteger(n, n + r);
            case 3:
                return this.sub ? "(" + this.sub.length + " elem)" : this.stream.parseBitString(n, n + r);
            case 4:
                return this.sub ? "(" + this.sub.length + " elem)" : this.stream.parseOctetString(n, n + r);
            case 6:
                return this.stream.parseOID(n, n + r);
            case 16:
            case 17:
                return "(" + this.sub.length + " elem)";
            case 12:
                return this.stream.parseStringUTF(n, n + r);
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 26:
                return this.stream.parseStringISO(n, n + r);
            case 23:
            case 24:
                return this.stream.parseTime(n, n + r)
        }
        return null
    }, e.prototype.toString = function () {
        return this.typeName() + "@" + this.stream.pos + "[header:" + this.header + ",length:" + this.length + ",sub:" + (null == this.sub ? "null" : this.sub.length) + "]"
    }, e.prototype.print = function (t) {
        if (void 0 == t && (t = ""), document.writeln(t + this), null != this.sub) {
            t += "  ";
            for (var e = 0, n = this.sub.length; n > e; ++e)this.sub[e].print(t)
        }
    }, e.prototype.toPrettyString = function (t) {
        void 0 == t && (t = "");
        var e = t + this.typeName() + " @" + this.stream.pos;
        if (this.length >= 0 && (e += "+"), e += this.length, 32 & this.tag ? e += " (constructed)" : 3 != this.tag && 4 != this.tag || null == this.sub || (e += " (encapsulates)"), e += "\n", null != this.sub) {
            t += "  ";
            for (var n = 0, r = this.sub.length; r > n; ++n)e += this.sub[n].toPrettyString(t)
        }
        return e
    }, e.prototype.posStart = function () {
        return this.stream.pos
    }, e.prototype.posContent = function () {
        return this.stream.pos + this.header
    }, e.prototype.posEnd = function () {
        return this.stream.pos + this.header + Math.abs(this.length)
    }, e.decodeLength = function (t) {
        var e = t.get(), n = 127 & e;
        if (n == e)return n;
        if (n > 3)throw"Length over 24 bits not supported at position " + (t.pos - 1);
        if (0 == n)return -1;
        e = 0;
        for (var r = 0; n > r; ++r)e = e << 8 | t.get();
        return e
    }, e.hasContent = function (n, r, i) {
        if (32 & n)return !0;
        if (3 > n || n > 4)return !1;
        var o = new t(i);
        3 == n && o.get();
        var s = o.get();
        if (s >> 6 & 1)return !1;
        try {
            var a = e.decodeLength(o);
            return o.pos - i.pos + a == r
        } catch (u) {
            return !1
        }
    }, e.decode = function (n) {
        n instanceof t || (n = new t(n, 0));
        var r = new t(n), i = n.get(), o = e.decodeLength(n), s = n.pos - r.pos, a = null;
        if (e.hasContent(i, o, n)) {
            var u = n.pos;
            if (3 == i && n.get(), a = [], o >= 0) {
                for (var c = u + o; n.pos < c;)a[a.length] = e.decode(n);
                if (n.pos != c)throw"Content size is not correct for container starting at offset " + u
            } else try {
                for (; ;) {
                    var l = e.decode(n);
                    if (0 == l.tag)break;
                    a[a.length] = l
                }
                o = u - n.pos
            } catch (p) {
                throw"Exception while decoding undefined length content: " + p
            }
        } else n.pos += o;
        return new e(r, s, o, i, a)
    };
    var te, ee = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", ne = "=", re = 0xdeadbeefcafe, ie = 15715070 == (16777215 & re);
    ie && "Microsoft Internet Explorer" == navigator.appName ? (o.prototype.am = u, te = 30) : ie && "Netscape" != navigator.appName ? (o.prototype.am = a, te = 26) : (o.prototype.am = c, te = 28), o.prototype.DB = te, o.prototype.DM = (1 << te) - 1, o.prototype.DV = 1 << te;
    var oe = 52;
    o.prototype.FV = Math.pow(2, oe), o.prototype.F1 = oe - te, o.prototype.F2 = 2 * te - oe;
    var se, ae, ue = "0123456789abcdefghijklmnopqrstuvwxyz", ce = new Array;
    for (se = "0".charCodeAt(0), ae = 0; 9 >= ae; ++ae)ce[se++] = ae;
    for (se = "a".charCodeAt(0), ae = 10; 36 > ae; ++ae)ce[se++] = ae;
    for (se = "A".charCodeAt(0), ae = 10; 36 > ae; ++ae)ce[se++] = ae;
    R.prototype.convert = k, R.prototype.revert = D, R.prototype.reduce = M, R.prototype.mulTo = U, R.prototype.sqrTo = L, j.prototype.convert = B, j.prototype.revert = z, j.prototype.reduce = H, j.prototype.mulTo = Y, j.prototype.sqrTo = V, o.prototype.copyTo = h, o.prototype.fromInt = d, o.prototype.fromString = m, o.prototype.clamp = g, o.prototype.dlShiftTo = C, o.prototype.drShiftTo = A, o.prototype.lShiftTo = S, o.prototype.rShiftTo = x, o.prototype.subTo = T, o.prototype.multiplyTo = O, o.prototype.squareTo = P, o.prototype.divRemTo = N, o.prototype.invDigit = F, o.prototype.isEven = q, o.prototype.exp = W, o.prototype.toString = y, o.prototype.negate = b, o.prototype.abs = v, o.prototype.compareTo = _, o.prototype.bitLength = w, o.prototype.mod = I, o.prototype.modPowInt = G, o.ZERO = f(0), o.ONE = f(1), $.prototype.doPublic = X, $.prototype.setPublic = Z, $.prototype.encrypt = J;
    var le = {
        cipher: {}, hash: {}, keyexchange: {}, mode: {}, misc: {}, codec: {}, exception: {
            corrupt: function (t) {
                this.toString = function () {
                    return "CORRUPT: " + this.message
                }, this.message = t
            }, invalid: function (t) {
                this.toString = function () {
                    return "INVALID: " + this.message
                }, this.message = t
            }, bug: function (t) {
                this.toString = function () {
                    return "BUG: " + this.message
                }, this.message = t
            }, notReady: function (t) {
                this.toString = function () {
                    return "NOT READY: " + this.message
                }, this.message = t
            }
        }
    };
    "undefined" != typeof module && module.exports && (module.exports = le), le.cipher.aes = function (t) {
        this._tables[0][0][0] || this._precompute();
        var e, n, r, i, o, s = this._tables[0][4], a = this._tables[1], u = t.length, c = 1;
        if (4 !== u && 6 !== u && 8 !== u)throw new le.exception.invalid("invalid aes key size");
        for (this._key = [i = t.slice(0), o = []], e = u; 4 * u + 28 > e; e++)r = i[e - 1], (e % u === 0 || 8 === u && e % u === 4) && (r = s[r >>> 24] << 24 ^ s[r >> 16 & 255] << 16 ^ s[r >> 8 & 255] << 8 ^ s[255 & r], e % u === 0 && (r = r << 8 ^ r >>> 24 ^ c << 24, c = c << 1 ^ 283 * (c >> 7))), i[e] = i[e - u] ^ r;
        for (n = 0; e; n++, e--)r = i[3 & n ? e : e - 4], o[n] = 4 >= e || 4 > n ? r : a[0][s[r >>> 24]] ^ a[1][s[r >> 16 & 255]] ^ a[2][s[r >> 8 & 255]] ^ a[3][s[255 & r]]
    }, le.cipher.aes.prototype = {
        encrypt: function (t) {
            return this._crypt(t, 0)
        }, decrypt: function (t) {
            return this._crypt(t, 1)
        }, _tables: [[[], [], [], [], []], [[], [], [], [], []]], _precompute: function () {
            var t, e, n, r, i, o, s, a, u, c = this._tables[0], l = this._tables[1], p = c[4], h = l[4], d = [], f = [];
            for (t = 0; 256 > t; t++)f[(d[t] = t << 1 ^ 283 * (t >> 7)) ^ t] = t;
            for (e = n = 0; !p[e]; e ^= r || 1, n = f[n] || 1)for (s = n ^ n << 1 ^ n << 2 ^ n << 3 ^ n << 4, s = s >> 8 ^ 255 & s ^ 99, p[e] = s, h[s] = e, o = d[i = d[r = d[e]]], u = 16843009 * o ^ 65537 * i ^ 257 * r ^ 16843008 * e, a = 257 * d[s] ^ 16843008 * s, t = 0; 4 > t; t++)c[t][e] = a = a << 24 ^ a >>> 8, l[t][s] = u = u << 24 ^ u >>> 8;
            for (t = 0; 5 > t; t++)c[t] = c[t].slice(0), l[t] = l[t].slice(0)
        }, _crypt: function (t, e) {
            if (4 !== t.length)throw new le.exception.invalid("invalid aes block size");
            var n, r, i, o, s = this._key[e], a = t[0] ^ s[0], u = t[e ? 3 : 1] ^ s[1], c = t[2] ^ s[2], l = t[e ? 1 : 3] ^ s[3], p = s.length / 4 - 2, h = 4, d = [0, 0, 0, 0], f = this._tables[e], m = f[0], g = f[1], y = f[2], b = f[3], v = f[4];
            for (o = 0; p > o; o++)n = m[a >>> 24] ^ g[u >> 16 & 255] ^ y[c >> 8 & 255] ^ b[255 & l] ^ s[h], r = m[u >>> 24] ^ g[c >> 16 & 255] ^ y[l >> 8 & 255] ^ b[255 & a] ^ s[h + 1], i = m[c >>> 24] ^ g[l >> 16 & 255] ^ y[a >> 8 & 255] ^ b[255 & u] ^ s[h + 2], l = m[l >>> 24] ^ g[a >> 16 & 255] ^ y[u >> 8 & 255] ^ b[255 & c] ^ s[h + 3], h += 4, a = n, u = r, c = i;
            for (o = 0; 4 > o; o++)d[e ? 3 & -o : o] = v[a >>> 24] << 24 ^ v[u >> 16 & 255] << 16 ^ v[c >> 8 & 255] << 8 ^ v[255 & l] ^ s[h++], n = a, a = u, u = c, c = l, l = n;
            return d
        }
    }, le.bitArray = {
        bitSlice: function (t, e, n) {
            return t = le.bitArray._shiftRight(t.slice(e / 32), 32 - (31 & e)).slice(1), void 0 === n ? t : le.bitArray.clamp(t, n - e)
        }, extract: function (t, e, n) {
            var r, i = Math.floor(-e - n & 31);
            return r = -32 & (e + n - 1 ^ e) ? t[e / 32 | 0] << 32 - i ^ t[e / 32 + 1 | 0] >>> i : t[e / 32 | 0] >>> i, r & (1 << n) - 1
        }, concat: function (t, e) {
            if (0 === t.length || 0 === e.length)return t.concat(e);
            var n = t[t.length - 1], r = le.bitArray.getPartial(n);
            return 32 === r ? t.concat(e) : le.bitArray._shiftRight(e, r, 0 | n, t.slice(0, t.length - 1))
        }, bitLength: function (t) {
            var e, n = t.length;
            return 0 === n ? 0 : (e = t[n - 1], 32 * (n - 1) + le.bitArray.getPartial(e))
        }, clamp: function (t, e) {
            if (32 * t.length < e)return t;
            t = t.slice(0, Math.ceil(e / 32));
            var n = t.length;
            return e = 31 & e, n > 0 && e && (t[n - 1] = le.bitArray.partial(e, t[n - 1] & 2147483648 >> e - 1, 1)), t
        }, partial: function (t, e, n) {
            return 32 === t ? e : (n ? 0 | e : e << 32 - t) + 1099511627776 * t
        }, getPartial: function (t) {
            return Math.round(t / 1099511627776) || 32
        }, equal: function (t, e) {
            if (le.bitArray.bitLength(t) !== le.bitArray.bitLength(e))return !1;
            var n, r = 0;
            for (n = 0; n < t.length; n++)r |= t[n] ^ e[n];
            return 0 === r
        }, _shiftRight: function (t, e, n, r) {
            var i, o, s = 0;
            for (void 0 === r && (r = []); e >= 32; e -= 32)r.push(n), n = 0;
            if (0 === e)return r.concat(t);
            for (i = 0; i < t.length; i++)r.push(n | t[i] >>> e), n = t[i] << 32 - e;
            return s = t.length ? t[t.length - 1] : 0, o = le.bitArray.getPartial(s), r.push(le.bitArray.partial(e + o & 31, e + o > 32 ? n : r.pop(), 1)), r
        }, _xor4: function (t, e) {
            return [t[0] ^ e[0], t[1] ^ e[1], t[2] ^ e[2], t[3] ^ e[3]]
        }
    }, le.codec.hex = {
        fromBits: function (t) {
            var e, n = "";
            for (e = 0; e < t.length; e++)n += ((0 | t[e]) + 0xf00000000000).toString(16).substr(4);
            return n.substr(0, le.bitArray.bitLength(t) / 4)
        }, toBits: function (t) {
            var e, n, r = [];
            for (t = t.replace(/\s|0x/g, ""), n = t.length, t += "00000000", e = 0; e < t.length; e += 8)r.push(0 ^ parseInt(t.substr(e, 8), 16));
            return le.bitArray.clamp(r, 4 * n)
        }
    }, le.codec.utf8String = {
        fromBits: function (t) {
            var e, n, r = "", i = le.bitArray.bitLength(t);
            for (e = 0; i / 8 > e; e++)0 === (3 & e) && (n = t[e / 4]), r += String.fromCharCode(n >>> 24), n <<= 8;
            return decodeURIComponent(escape(r))
        }, toBits: function (t) {
            t = unescape(encodeURIComponent(t));
            var e, n = [], r = 0;
            for (e = 0; e < t.length; e++)r = r << 8 | t.charCodeAt(e), 3 === (3 & e) && (n.push(r), r = 0);
            return 3 & e && n.push(le.bitArray.partial(8 * (3 & e), r)), n
        }
    }, le.codec.base64 = {
        _chars: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
        fromBits: function (t, e, n) {
            var r, i = "", o = 0, s = le.codec.base64._chars, a = 0, u = le.bitArray.bitLength(t);
            for (n && (s = s.substr(0, 62) + "-_"), r = 0; 6 * i.length < u;)i += s.charAt((a ^ t[r] >>> o) >>> 26), 6 > o ? (a = t[r] << 6 - o, o += 26, r++) : (a <<= 6, o -= 6);
            for (; 3 & i.length && !e;)i += "=";
            return i
        },
        toBits: function (t, e) {
            t = t.replace(/\s|=/g, "");
            var n, r, i = [], o = 0, s = le.codec.base64._chars, a = 0;
            for (e && (s = s.substr(0, 62) + "-_"), n = 0; n < t.length; n++) {
                if (r = s.indexOf(t.charAt(n)), 0 > r)throw new le.exception.invalid("this isn't base64!");
                o > 26 ? (o -= 26, i.push(a ^ r >>> o), a = r << 32 - o) : (o += 6, a ^= r << 32 - o)
            }
            return 56 & o && i.push(le.bitArray.partial(56 & o, a, 1)), i
        }
    }, le.codec.base64url = {
        fromBits: function (t) {
            return le.codec.base64.fromBits(t, 1, 1)
        }, toBits: function (t) {
            return le.codec.base64.toBits(t, 1)
        }
    }, void 0 === le.beware && (le.beware = {}), le.beware["CBC mode is dangerous because it doesn't protect message integrity."] = function () {
        le.mode.cbc = {
            name: "cbc", encrypt: function (t, e, n, r) {
                if (r && r.length)throw new le.exception.invalid("cbc can't authenticate data");
                if (128 !== le.bitArray.bitLength(n))throw new le.exception.invalid("cbc iv must be 128 bits");
                var i, o = le.bitArray, s = o._xor4, a = o.bitLength(e), u = 0, c = [];
                if (7 & a)throw new le.exception.invalid("pkcs#5 padding only works for multiples of a byte");
                for (i = 0; a >= u + 128; i += 4, u += 128)n = t.encrypt(s(n, e.slice(i, i + 4))), c.splice(i, 0, n[0], n[1], n[2], n[3]);
                return a = 16843009 * (16 - (a >> 3 & 15)), n = t.encrypt(s(n, o.concat(e, [a, a, a, a]).slice(i, i + 4))), c.splice(i, 0, n[0], n[1], n[2], n[3]), c
            }, decrypt: function (t, e, n, r) {
                if (r && r.length)throw new le.exception.invalid("cbc can't authenticate data");
                if (128 !== le.bitArray.bitLength(n))throw new le.exception.invalid("cbc iv must be 128 bits");
                if (127 & le.bitArray.bitLength(e) || !e.length)throw new le.exception.corrupt("cbc ciphertext must be a positive multiple of the block size");
                var i, o, s, a = le.bitArray, u = a._xor4, c = [];
                for (r = r || [], i = 0; i < e.length; i += 4)o = e.slice(i, i + 4), s = u(n, t.decrypt(o)), c.splice(i, 0, s[0], s[1], s[2], s[3]), n = o;
                if (o = 255 & c[i - 1], 0 == o || o > 16)throw new le.exception.corrupt("pkcs#5 padding corrupt");
                if (s = 16843009 * o, !a.equal(a.bitSlice([s, s, s, s], 0, 8 * o), a.bitSlice(c, 32 * c.length - 8 * o, 32 * c.length)))throw new le.exception.corrupt("pkcs#5 padding corrupt");
                return a.bitSlice(c, 0, 32 * c.length - 8 * o)
            }
        }
    }, le.misc.hmac = function (t, e) {
        this._hash = e = e || le.hash.sha256;
        var n, r = [[], []], i = e.prototype.blockSize / 32;
        for (this._baseHash = [new e, new e], t.length > i && (t = e.hash(t)), n = 0; i > n; n++)r[0][n] = 909522486 ^ t[n], r[1][n] = 1549556828 ^ t[n];
        this._baseHash[0].update(r[0]), this._baseHash[1].update(r[1])
    }, le.misc.hmac.prototype.encrypt = le.misc.hmac.prototype.mac = function (t, e) {
        var n = new this._hash(this._baseHash[0]).update(t, e).finalize();
        return new this._hash(this._baseHash[1]).update(n).finalize()
    }, le.hash.sha256 = function (t) {
        this._key[0] || this._precompute(), t ? (this._h = t._h.slice(0), this._buffer = t._buffer.slice(0), this._length = t._length) : this.reset()
    }, le.hash.sha256.hash = function (t) {
        return (new le.hash.sha256).update(t).finalize()
    }, le.hash.sha256.prototype = {
        blockSize: 512, reset: function () {
            return this._h = this._init.slice(0), this._buffer = [], this._length = 0, this
        }, update: function (t) {
            "string" == typeof t && (t = le.codec.utf8String.toBits(t));
            var e, n = this._buffer = le.bitArray.concat(this._buffer, t), r = this._length, i = this._length = r + le.bitArray.bitLength(t);
            for (e = 512 + r & -512; i >= e; e += 512)this._block(n.splice(0, 16));
            return this
        }, finalize: function () {
            var t, e = this._buffer, n = this._h;
            for (e = le.bitArray.concat(e, [le.bitArray.partial(1, 1)]), t = e.length + 2; 15 & t; t++)e.push(0);
            for (e.push(Math.floor(this._length / 4294967296)), e.push(0 | this._length); e.length;)this._block(e.splice(0, 16));
            return this.reset(), n
        }, _init: [], _key: [], _precompute: function () {
            function t(t) {
                return 4294967296 * (t - Math.floor(t)) | 0
            }

            var e, n = 0, r = 2;
            t:for (; 64 > n; r++) {
                for (e = 2; r >= e * e; e++)if (r % e === 0)continue t;
                8 > n && (this._init[n] = t(Math.pow(r, .5))), this._key[n] = t(Math.pow(r, 1 / 3)), n++
            }
        }, _block: function (t) {
            var e, n, r, i, o = t.slice(0), s = this._h, a = this._key, u = s[0], c = s[1], l = s[2], p = s[3], h = s[4], d = s[5], f = s[6], m = s[7];
            for (e = 0; 64 > e; e++)16 > e ? n = o[e] : (r = o[e + 1 & 15], i = o[e + 14 & 15], n = o[15 & e] = (r >>> 7 ^ r >>> 18 ^ r >>> 3 ^ r << 25 ^ r << 14) + (i >>> 17 ^ i >>> 19 ^ i >>> 10 ^ i << 15 ^ i << 13) + o[15 & e] + o[e + 9 & 15] | 0), n = n + m + (h >>> 6 ^ h >>> 11 ^ h >>> 25 ^ h << 26 ^ h << 21 ^ h << 7) + (f ^ h & (d ^ f)) + a[e], m = f, f = d, d = h, h = p + n | 0, p = l, l = c, c = u, u = n + (c & l ^ p & (c ^ l)) + (c >>> 2 ^ c >>> 13 ^ c >>> 22 ^ c << 30 ^ c << 19 ^ c << 10) | 0;
            s[0] = s[0] + u | 0, s[1] = s[1] + c | 0, s[2] = s[2] + l | 0, s[3] = s[3] + p | 0, s[4] = s[4] + h | 0, s[5] = s[5] + d | 0, s[6] = s[6] + f | 0, s[7] = s[7] + m | 0
        }
    }, le.random = {
        randomWords: function (t, e) {
            var n, r, i = [], o = this.isReady(e);
            if (o === this._NOT_READY)throw new le.exception.notReady("generator isn't seeded");
            for (o & this._REQUIRES_RESEED && this._reseedFromPools(!(o & this._READY)), n = 0; t > n; n += 4)(n + 1) % this._MAX_WORDS_PER_BURST === 0 && this._gate(), r = this._gen4words(), i.push(r[0], r[1], r[2], r[3]);
            return this._gate(), i.slice(0, t)
        },
        setDefaultParanoia: function (t) {
            this._defaultParanoia = t
        },
        addEntropy: function (t, e, n) {
            n = n || "user";
            var r, i, o, s = (new Date).valueOf(), a = this._robins[n], u = this.isReady(), c = 0;
            switch (r = this._collectorIds[n], void 0 === r && (r = this._collectorIds[n] = this._collectorIdNext++), void 0 === a && (a = this._robins[n] = 0), this._robins[n] = (this._robins[n] + 1) % this._pools.length, typeof t) {
                case"number":
                    void 0 === e && (e = 1), this._pools[a].update([r, this._eventId++, 1, e, s, 1, 0 | t]);
                    break;
                case"object":
                    var l = Object.prototype.toString.call(t);
                    if ("[object Uint32Array]" === l) {
                        for (o = [], i = 0; i < t.length; i++)o.push(t[i]);
                        t = o
                    } else for ("[object Array]" !== l && (c = 1), i = 0; i < t.length && !c; i++)"number" != typeof t[i] && (c = 1);
                    if (!c) {
                        if (void 0 === e)for (e = 0, i = 0; i < t.length; i++)for (o = t[i]; o > 0;)e++, o >>>= 1;
                        this._pools[a].update([r, this._eventId++, 2, e, s, t.length].concat(t))
                    }
                    break;
                case"string":
                    void 0 === e && (e = t.length), this._pools[a].update([r, this._eventId++, 3, e, s, t.length]), this._pools[a].update(t);
                    break;
                default:
                    c = 1
            }
            if (c)throw new le.exception.bug("random: addEntropy only supports number, array of numbers or string");
            this._poolEntropy[a] += e, this._poolStrength += e, u === this._NOT_READY && (this.isReady() !== this._NOT_READY && this._fireEvent("seeded", Math.max(this._strength, this._poolStrength)), this._fireEvent("progress", this.getProgress()))
        },
        isReady: function (t) {
            var e = this._PARANOIA_LEVELS[void 0 !== t ? t : this._defaultParanoia];
            return this._strength && this._strength >= e ? this._poolEntropy[0] > this._BITS_PER_RESEED && (new Date).valueOf() > this._nextReseed ? this._REQUIRES_RESEED | this._READY : this._READY : this._poolStrength >= e ? this._REQUIRES_RESEED | this._NOT_READY : this._NOT_READY
        },
        getProgress: function (t) {
            var e = this._PARANOIA_LEVELS[t ? t : this._defaultParanoia];
            return this._strength >= e ? 1 : this._poolStrength > e ? 1 : this._poolStrength / e
        },
        startCollectors: function () {
            if (!this._collectorsStarted) {
                if (window.addEventListener)window.addEventListener("load", this._loadTimeCollector, !1), window.addEventListener("mousemove", this._mouseCollector, !1); else {
                    if (!document.attachEvent)throw new le.exception.bug("can't attach event");
                    document.attachEvent("onload", this._loadTimeCollector), document.attachEvent("onmousemove", this._mouseCollector)
                }
                this._collectorsStarted = !0
            }
        },
        stopCollectors: function () {
            this._collectorsStarted && (window.removeEventListener ? (window.removeEventListener("load", this._loadTimeCollector, !1), window.removeEventListener("mousemove", this._mouseCollector, !1)) : window.detachEvent && (window.detachEvent("onload", this._loadTimeCollector), window.detachEvent("onmousemove", this._mouseCollector)), this._collectorsStarted = !1)
        },
        addEventListener: function (t, e) {
            this._callbacks[t][this._callbackI++] = e
        },
        removeEventListener: function (t, e) {
            var n, r, i = this._callbacks[t], o = [];
            for (r in i)i.hasOwnProperty(r) && i[r] === e && o.push(r);
            for (n = 0; n < o.length; n++)r = o[n], delete i[r]
        },
        _pools: [new le.hash.sha256],
        _poolEntropy: [0],
        _reseedCount: 0,
        _robins: {},
        _eventId: 0,
        _collectorIds: {},
        _collectorIdNext: 0,
        _strength: 0,
        _poolStrength: 0,
        _nextReseed: 0,
        _key: [0, 0, 0, 0, 0, 0, 0, 0],
        _counter: [0, 0, 0, 0],
        _cipher: void 0,
        _defaultParanoia: 6,
        _collectorsStarted: !1,
        _callbacks: {progress: {}, seeded: {}},
        _callbackI: 0,
        _NOT_READY: 0,
        _READY: 1,
        _REQUIRES_RESEED: 2,
        _MAX_WORDS_PER_BURST: 65536,
        _PARANOIA_LEVELS: [0, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024],
        _MILLISECONDS_PER_RESEED: 3e4,
        _BITS_PER_RESEED: 80,
        _gen4words: function () {
            for (var t = 0; 4 > t && (this._counter[t] = this._counter[t] + 1 | 0, !this._counter[t]); t++);
            return this._cipher.encrypt(this._counter)
        },
        _gate: function () {
            this._key = this._gen4words().concat(this._gen4words()), this._cipher = new le.cipher.aes(this._key)
        },
        _reseed: function (t) {
            this._key = le.hash.sha256.hash(this._key.concat(t)), this._cipher = new le.cipher.aes(this._key);
            for (var e = 0; 4 > e && (this._counter[e] = this._counter[e] + 1 | 0, !this._counter[e]); e++);
        },
        _reseedFromPools: function (t) {
            var e, n = [], r = 0;
            for (this._nextReseed = n[0] = (new Date).valueOf() + this._MILLISECONDS_PER_RESEED, e = 0; 16 > e; e++)n.push(4294967296 * Math.random() | 0);
            for (e = 0; e < this._pools.length && (n = n.concat(this._pools[e].finalize()), r += this._poolEntropy[e], this._poolEntropy[e] = 0, t || !(this._reseedCount & 1 << e)); e++);
            this._reseedCount >= 1 << this._pools.length && (this._pools.push(new le.hash.sha256), this._poolEntropy.push(0)), this._poolStrength -= r, r > this._strength && (this._strength = r), this._reseedCount++, this._reseed(n)
        },
        _mouseCollector: function (t) {
            var e = t.x || t.clientX || t.offsetX || 0, n = t.y || t.clientY || t.offsetY || 0;
            le.random.addEntropy([e, n], 2, "mouse")
        },
        _loadTimeCollector: function () {
            le.random.addEntropy((new Date).valueOf(), 2, "loadtime")
        },
        _fireEvent: function (t, e) {
            var n, r = le.random._callbacks[t], i = [];
            for (n in r)r.hasOwnProperty(n) && i.push(r[n]);
            for (n = 0; n < i.length; n++)i[n](e)
        }
    }, function () {
        try {
            var t = new Uint32Array(32);
            crypto.getRandomValues(t), le.random.addEntropy(t, 1024, "crypto.getRandomValues")
        } catch (e) {
        }
    }(), function () {
        for (var t in le.beware)le.beware.hasOwnProperty(t) && le.beware[t]()
    }();
    var pe = {sjcl: le, version: "1.3.10"};
    pe.generateAesKey = function () {
        return {
            key: le.random.randomWords(8, 0), encrypt: function (t) {
                return this.encryptWithIv(t, le.random.randomWords(4, 0))
            }, encryptWithIv: function (t, e) {
                var n = new le.cipher.aes(this.key), r = le.codec.utf8String.toBits(t), i = le.mode.cbc.encrypt(n, r, e), o = le.bitArray.concat(e, i);
                return le.codec.base64.fromBits(o)
            }
        }
    }, pe.create = function (t) {
        return new pe.EncryptionClient(t)
    }, pe.EncryptionClient = function (t) {
        var r = this, o = [];
        r.publicKey = t, r.version = pe.version;
        var s = function (t, e) {
            var n, r, i;
            n = document.createElement(t);
            for (r in e)e.hasOwnProperty(r) && (i = e[r], n.setAttribute(r, i));
            return n
        }, a = function (t) {
            return window.jQuery && t instanceof jQuery ? t[0] : t.nodeType && 1 === t.nodeType ? t : document.getElementById(t)
        }, u = function (t) {
            var e, n, r, i, o = [];
            if ("INTEGER" === t.typeName() && (e = t.posContent(), n = t.posEnd(), r = t.stream.hexDump(e, n).replace(/[ \n]/g, ""), o.push(r)), null !== t.sub)for (i = 0; i < t.sub.length; i++)o = o.concat(u(t.sub[i]));
            return o
        }, c = function (t) {
            var e, n, r = [], i = t.children;
            for (n = 0; n < i.length; n++)e = i[n], 1 === e.nodeType && e.attributes["data-encrypted-name"] ? r.push(e) : e.children && e.children.length > 0 && (r = r.concat(c(e)));
            return r
        }, l = function () {
            var n, r, o, s, a, c;
            try {
                a = i(t), n = e.decode(a)
            } catch (l) {
                throw"Invalid encryption key. Please use the key labeled 'Client-Side Encryption Key'"
            }
            if (o = u(n), 2 !== o.length)throw"Invalid encryption key. Please use the key labeled 'Client-Side Encryption Key'";
            return s = o[0], r = o[1], c = new $, c.setPublic(s, r), c
        }, p = function () {
            return {
                key: le.random.randomWords(8, 0), sign: function (t) {
                    var e = new le.misc.hmac(this.key, le.hash.sha256), n = e.encrypt(t);
                    return le.codec.base64.fromBits(n)
                }
            }
        };
        r.encrypt = function (t) {
            var e = l(), i = pe.generateAesKey(), o = p(), s = i.encrypt(t), a = o.sign(le.codec.base64.toBits(s)), u = le.bitArray.concat(i.key, o.key), c = le.codec.base64.fromBits(u), h = e.encrypt(c), d = "$bt4|javascript_" + r.version.replace(/\./g, "_") + "$", f = null;
            return h && (f = n(h)), d + f + "$" + s + "$" + a
        }, r.encryptForm = function (t) {
            var e, n, i, u, l, p;
            for (t = a(t), p = c(t); o.length > 0;) {
                try {
                    t.removeChild(o[0])
                } catch (h) {
                }
                o.splice(0, 1)
            }
            for (l = 0; l < p.length; l++)e = p[l], i = e.getAttribute("data-encrypted-name"), n = r.encrypt(e.value), e.removeAttribute("name"), u = s("input", {
                value: n,
                type: "hidden",
                name: i
            }), o.push(u), t.appendChild(u)
        }, r.onSubmitEncryptForm = function (t, e) {
            var n;
            t = a(t), n = function (n) {
                return r.encryptForm(t), e ? e(n) : n
            }, window.jQuery ? window.jQuery(t).submit(n) : t.addEventListener ? t.addEventListener("submit", n, !1) : t.attachEvent && t.attachEvent("onsubmit", n)
        }, r.formEncrypter = {
            encryptForm: r.encryptForm,
            extractForm: a,
            onSubmitEncryptForm: r.onSubmitEncryptForm
        }, le.random.startCollectors()
    }, window.Braintree = pe
}(), !function (t) {
    if ("object" == typeof exports && "undefined" != typeof module)module.exports = t(); else if ("function" == typeof define && define.amd)define([], t); else {
        var e;
        "undefined" != typeof window ? e = window : "undefined" != typeof global ? e = global : "undefined" != typeof self && (e = self), e.braintree = t()
    }
}(function () {
    var t;
    return function e(t, n, r) {
        function i(s, a) {
            if (!n[s]) {
                if (!t[s]) {
                    var u = "function" == typeof require && require;
                    if (!a && u)return u(s, !0);
                    if (o)return o(s, !0);
                    var c = new Error("Cannot find module '" + s + "'");
                    throw c.code = "MODULE_NOT_FOUND", c
                }
                var l = n[s] = {exports: {}};
                t[s][0].call(l.exports, function (e) {
                    var n = t[s][1][e];
                    return i(n ? n : e)
                }, l, l.exports, e, t, n, r)
            }
            return n[s].exports
        }

        for (var o = "function" == typeof require && require, s = 0; s < r.length; s++)i(r[s]);
        return i
    }({
        1: [function (t, e) {
            (function (n) {
                "use strict";
                function r() {
                }

                function i(t) {
                    if ("CONFIGURATION" === t.type || "IMMEDIATE" === t.type)throw new Error(t.message);
                    try {
                        console.error(JSON.stringify(t))
                    } catch (e) {
                    }
                }

                function o(t, e, r) {
                    var i;
                    if (!(e in h))throw new Error(e + " is an unsupported integration");
                    p.isFunction(r[f.ROOT_SUCCESS_CALLBACK]) && (b = function (t) {
                        r[f.ROOT_SUCCESS_CALLBACK](g(t))
                    }), p.isFunction(r[f.ROOT_ERROR_CALLBACK]) && (_ = r[f.ROOT_ERROR_CALLBACK]), p.isFunction(r[f.ROOT_READY_CALLBACK]) && (v = r[f.ROOT_READY_CALLBACK]), m(v), d.on(d.events.ERROR, _), d.on(d.events.PAYMENT_METHOD_RECEIVED, b), d.on(d.events.WARNING, function (t) {
                        try {
                            console.warn(t)
                        } catch (e) {
                        }
                    }), i = {
                        clientToken: t,
                        integrationType: e,
                        merchantConfiguration: r,
                        analyticsConfiguration: {sdkVersion: "braintree/web" + s, merchantAppId: n.location.host}
                    }, a._getConfiguration(i, function (t, n) {
                        return t ? void d.emit(d.events.ERROR, {message: t.errors}) : (i.gatewayConfiguration = n, y(i), d.on(d.events.CONFIGURATION_REQUEST, function (t) {
                            t({
                                enableCORS: i.merchantConfiguration.enableCORS,
                                configuration: i.gatewayConfiguration,
                                integration: i.integrationType,
                                analyticsConfiguration: i.analyticsConfiguration
                            })
                        }), d.emit(d.events.ASYNC_DEPENDENCY_INITIALIZING), h[e].initialize(i), void d.emit(d.events.ASYNC_DEPENDENCY_READY))
                    })
                }

                var s = "2.11.2", a = t("braintree-api"), u = t("braintree-paypal"), c = t("braintree-dropin"), l = t("braintree-form"), p = t("braintree-utilities"), h = t("./integrations"), d = t("braintree-bus"), f = t("./constants"), m = t("./lib/dependency-ready"), g = t("./lib/sanitize-payload"), y = t("./lib/analytics-sender").listenForAnalytics, b = r, v = r, _ = i;
                e.exports = {api: a, cse: n.Braintree, paypal: u, dropin: c, Form: l, setup: o, VERSION: s}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {
            "./constants": 358,
            "./integrations": 362,
            "./lib/analytics-sender": 364,
            "./lib/dependency-ready": 365,
            "./lib/sanitize-payload": 366,
            "braintree-api": 21,
            "braintree-bus": 50,
            "braintree-dropin": 246,
            "braintree-form": 256,
            "braintree-paypal": 324,
            "braintree-utilities": 344
        }],
        2: [function (t, e) {
            (function (n) {
                "use strict";
                function r(t) {
                    var e = t.analyticsConfiguration || {}, r = n.braintree ? n.braintree.VERSION : null, i = r ? "braintree/web/" + r : "";
                    return {sdkVersion: e.sdkVersion || i, merchantAppId: e.merchantAppId || n.location.host}
                }

                function i(t) {
                    var e, n, i;
                    this.attrs = {}, t.hasOwnProperty("sharedCustomerIdentifier") && (this.attrs.sharedCustomerIdentifier = t.sharedCustomerIdentifier), e = a(t.clientToken), i = r(t), this.driver = t.driver || m({enableCORS: g(t)}), this.analyticsUrl = e.analytics ? e.analytics.url : void 0, this.clientApiUrl = e.clientApiUrl, this.customerId = t.customerId, this.challenges = e.challenges, this.integration = t.integration || "", this.sdkVersion = i.sdkVersion, this.merchantAppId = i.merchantAppId, n = s.create(this, {
                        container: t.container,
                        clientToken: e
                    }), this.verify3DS = o.bind(n.verify, n), this.attrs.authorizationFingerprint = e.authorizationFingerprint, this.attrs.sharedCustomerIdentifierType = t.sharedCustomerIdentifierType, e.merchantAccountId && (this.attrs.merchantAccountId = e.merchantAccountId), this.requestTimeout = t.hasOwnProperty("timeout") ? t.timeout : 6e4
                }

                var o = t("braintree-utilities"), s = t("braintree-3ds"), a = t("./parse-client-token"), u = t("./util"), c = t("./sepa-mandate"), l = t("./europe-bank-account"), p = t("./credit-card"), h = t("./coinbase-account"), d = t("./paypal-account"), f = t("./normalize-api-fields").normalizeCreditCardFields, m = t("./request/choose-driver"), g = t("./should-enable-cors");
                i.prototype.getCreditCards = function (t) {
                    this.driver.get(u.joinUrlFragments([this.clientApiUrl, "v1", "payment_methods"]), this.attrs, function (t) {
                        var e = 0, n = t.paymentMethods.length, r = [];
                        for (e; n > e; e++)r.push(new p(t.paymentMethods[e]));
                        return r
                    }, t, this.requestTimeout)
                }, i.prototype.tokenizeCoinbase = function (t, e) {
                    t.options = {validate: !1}, this.addCoinbase(t, function (t, n) {
                        t ? e(t, null) : n && n.nonce ? e(t, n) : e("Unable to tokenize coinbase account.", null)
                    })
                }, i.prototype.tokenizePayPalAccount = function (t, e) {
                    t.options = {validate: !1}, this.addPayPalAccount(t, function (t, n) {
                        t ? e(t, null) : n && n.nonce ? e(null, n) : e("Unable to tokenize paypal account.", null)
                    })
                }, i.prototype.tokenizeCard = function (t, e) {
                    t.options = {validate: !1}, this.addCreditCard(t, function (t, n) {
                        n && n.nonce ? e(t, n.nonce, {
                            type: n.type,
                            details: n.details
                        }) : e("Unable to tokenize card.", null)
                    })
                }, i.prototype.lookup3DS = function (t, e) {
                    var n = u.joinUrlFragments([this.clientApiUrl, "v1/payment_methods", t.nonce, "three_d_secure/lookup"]), r = u.mergeOptions(this.attrs, {amount: t.amount});
                    this.driver.post(n, r, function (t) {
                        return t
                    }, e, this.requestTimeout)
                }, i.prototype.createSEPAMandate = function (t, e) {
                    var n = u.mergeOptions(this.attrs, {sepaMandate: t});
                    this.driver.post(u.joinUrlFragments([this.clientApiUrl, "v1", "sepa_mandates.json"]), n, function (t) {
                        return {
                            sepaMandate: new c(t.europeBankAccounts[0].sepaMandates[0]),
                            sepaBankAccount: new l(t.europeBankAccounts[0])
                        }
                    }, e, this.requestTimeout)
                }, i.prototype.getSEPAMandate = function (t, e) {
                    var n = u.mergeOptions(this.attrs, t);
                    this.driver.get(u.joinUrlFragments([this.clientApiUrl, "v1", "sepa_mandates.json"]), n, function (t) {
                        return {sepaMandate: new c(t.sepaMandates[0])}
                    }, e, this.requestTimeout)
                }, i.prototype.addCoinbase = function (t, e) {
                    var n;
                    delete t.share, n = u.mergeOptions(this.attrs, {
                        coinbaseAccount: t,
                        _meta: {integration: this.integration || "custom", source: "coinbase"}
                    }), this.driver.post(u.joinUrlFragments([this.clientApiUrl, "v1", "payment_methods/coinbase_accounts"]), n, function (t) {
                        return new h(t.coinbaseAccounts[0])
                    }, e, this.requestTimeout)
                }, i.prototype.addPayPalAccount = function (t, e) {
                    var n;
                    delete t.share, n = u.mergeOptions(this.attrs, {
                        paypalAccount: t,
                        _meta: {integration: this.integration || "paypal", source: "paypal"}
                    }), this.driver.post(u.joinUrlFragments([this.clientApiUrl, "v1", "payment_methods", "paypal_accounts"]), n, function (t) {
                        return new d(t.paypalAccounts[0])
                    }, e, this.requestTimeout)
                }, i.prototype.addCreditCard = function (t, e) {
                    var n, r, i = t.share;
                    delete t.share, r = f(t), n = u.mergeOptions(this.attrs, {
                        share: i,
                        creditCard: r,
                        _meta: {integration: this.integration || "custom", source: "form"}
                    }), this.driver.post(u.joinUrlFragments([this.clientApiUrl, "v1", "payment_methods/credit_cards"]), n, function (t) {
                        return new p(t.creditCards[0])
                    }, e, this.requestTimeout)
                }, i.prototype.sendAnalyticsEvents = function (t, e) {
                    var r, i, o = this.analyticsUrl, s = [];
                    if (t = u.isArray(t) ? t : [t], !o)return void(e && e.apply(null, [null, {}]));
                    for (i in t)t.hasOwnProperty(i) && s.push({kind: t[i]});
                    r = u.mergeOptions(this.attrs, {
                        braintree_library_version: this.sdkVersion,
                        analytics: s,
                        _meta: {
                            merchantAppId: this.merchantAppId,
                            platform: "web",
                            platformVersion: n.navigator.userAgent,
                            integrationType: this.integration,
                            sdkVersion: this.sdkVersion
                        }
                    }), this.driver.post(o, r, function (t) {
                        return t
                    }, e, this.requestTimeout)
                }, i.prototype.decryptBrowserswitchPayload = function (t, e) {
                    var n = u.mergeOptions(this.attrs, {asymmetric_encrypted_payload: t}), r = u.joinUrlFragments([this.clientApiUrl, "/v1/paypal_browser_switch/decrypt"]);
                    this.driver.post(r, n, function (t) {
                        return t
                    }, e, this.requestTimeout)
                }, i.prototype.encryptBrowserswitchReturnPayload = function (t, e, n) {
                    var r = u.mergeOptions(this.attrs, {
                        payload: t,
                        aesKey: e
                    }), i = u.joinUrlFragments([this.clientApiUrl, "/v1/paypal_browser_switch/encrypt"]);
                    this.driver.post(i, r, function (t) {
                        return t
                    }, n, this.requestTimeout)
                }, i.prototype.exchangePaypalTokenForConsentCode = function (t, e) {
                    var n = u.mergeOptions(this.attrs, t);
                    this.attrs.merchantAccountId && (n.merchant_account_id = this.attrs.merchantAccountId);
                    var r = u.joinUrlFragments([this.clientApiUrl, "/v1/paypal_account_service/merchant_consent"]);
                    this.driver.post(r, n, function (t) {
                        return t
                    }, e, this.requestTimeout)
                }, e.exports = i
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {
            "./coinbase-account": 3,
            "./credit-card": 5,
            "./europe-bank-account": 6,
            "./normalize-api-fields": 8,
            "./parse-client-token": 9,
            "./paypal-account": 10,
            "./request/choose-driver": 13,
            "./sepa-mandate": 18,
            "./should-enable-cors": 19,
            "./util": 20,
            "braintree-3ds": 29,
            "braintree-utilities": 41
        }],
        3: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n;
                for (e = 0; e < r.length; e++)n = r[e], this[n] = t[n]
            }

            var r = ["nonce", "type", "description", "details"];
            e.exports = n
        }, {}],
        4: [function (t, e) {
            e.exports = {errors: {UNKNOWN_ERROR: "Unknown error", INVALID_TIMEOUT: "Timeout must be a number"}}
        }, {}],
        5: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n;
                for (e = 0; e < r.length; e++)n = r[e], this[n] = t[n]
            }

            var r = ["billingAddress", "branding", "createdAt", "createdAtMerchant", "createdAtMerchantName", "details", "isLocked", "lastUsedAt", "lastUsedAtMerchant", "lastUsedAtMerchantName", "lastUsedByCurrentMerchant", "nonce", "securityQuestions", "type"];
            e.exports = n
        }, {}],
        6: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n = ["bic", "maskedIBAN", "nonce", "accountHolderName"], r = 0;
                for (r = 0; r < n.length; r++)e = n[r], this[e] = t[e]
            }

            e.exports = n
        }, {}],
        7: [function (t, e) {
            "use strict";
            function n(t, e) {
                var n = r(t.clientToken), a = {authorizationFingerprint: n.authorizationFingerprint}, u = o({enableCORS: s(t)});
                u.get(n.configUrl, a, function (t) {
                    return i.mergeOptions(n, t)
                }, e, t.timeout)
            }

            var r = t("./parse-client-token"), i = t("./util"), o = t("./request/choose-driver"), s = t("./should-enable-cors");
            e.exports = n
        }, {"./parse-client-token": 9, "./request/choose-driver": 13, "./should-enable-cors": 19, "./util": 20}],
        8: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n = {billingAddress: t.billingAddress || {}};
                for (e in t)if (t.hasOwnProperty(e))switch (e.replace(/_/, "").toLowerCase()) {
                    case"postalcode":
                    case"countryname":
                    case"countrycodenumeric":
                    case"countrycodealpha2":
                    case"countrycodealpha3":
                    case"region":
                    case"extendedaddress":
                    case"locality":
                    case"firstname":
                    case"lastname":
                    case"company":
                    case"streetaddress":
                        n.billingAddress[e] = t[e];
                        break;
                    default:
                        n[e] = t[e]
                }
                return n
            }

            e.exports = {normalizeCreditCardFields: n}
        }, {}],
        9: [function (t, e) {
            "use strict";
            function n(t) {
                var e;
                if (!t)throw new Error("Braintree API Client Misconfigured: clientToken required.");
                if ("object" == typeof t && null !== t)e = t; else {
                    try {
                        t = window.atob(t)
                    } catch (n) {
                    }
                    try {
                        e = JSON.parse(t)
                    } catch (i) {
                        throw new Error("Braintree API Client Misconfigured: clientToken is not valid JSON.")
                    }
                }
                if (!e.hasOwnProperty("clientApiUrl") || !r.isWhitelistedDomain(e.clientApiUrl))throw new Error("Braintree API Client Misconfigured: the clientApiUrl provided in the clientToken is invalid.");
                return e
            }

            var r = t("braintree-utilities");
            t("./polyfill"), e.exports = n
        }, {"./polyfill": 11, "braintree-utilities": 41}],
        10: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n;
                for (e = 0; e < r.length; e++)n = r[e], this[n] = t[n]
            }

            var r = ["nonce", "type", "description", "details"];
            e.exports = n
        }, {}],
        11: [function (t, e) {
            (function (t) {
                "use strict";
                var n = function (t) {
                    var e = new RegExp("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})([=]{1,2})?$"), n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", r = "";
                    if (!e.test(t))throw new Error("Non base64 encoded input passed to window.atob polyfill");
                    var i = 0;
                    do {
                        var o = n.indexOf(t.charAt(i++)), s = n.indexOf(t.charAt(i++)), a = n.indexOf(t.charAt(i++)), u = n.indexOf(t.charAt(i++)), c = (63 & o) << 2 | s >> 4 & 3, l = (15 & s) << 4 | a >> 2 & 15, p = (3 & a) << 6 | 63 & u;
                        r += String.fromCharCode(c) + (l ? String.fromCharCode(l) : "") + (p ? String.fromCharCode(p) : "")
                    } while (i < t.length);
                    return r
                };
                t.atob = t.atob || n, e.exports = {atobPolyfill: n}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {}],
        12: [function (t, e) {
            (function (n) {
                "use strict";
                function r() {
                    return p ? new XMLHttpRequest : new XDomainRequest
                }

                function i(t, e, n, r, i) {
                    var o = a.createURLParams(t, e);
                    s("GET", o, null, n, r, i)
                }

                function o(t, e, n, r, i) {
                    s("POST", t, e, n, r, i)
                }

                function s(t, e, n, i, o, s) {
                    var a, h, d = r();
                    o = o || function () {
                    }, p ? d.onreadystatechange = function () {
                        4 === d.readyState && (a = d.status, h = c(d.responseText), a >= 400 || 0 === a ? o.call(null, h || {errors: l.errors.UNKNOWN_ERROR}, null) : a > 0 && o.call(null, null, i(h)))
                    } : (d.onload = function () {
                        o.call(null, null, i(c(d.responseText)))
                    }, d.onerror = function () {
                        o.call(null, d.responseText, null)
                    }, d.onprogress = function () {
                    }, d.ontimeout = function () {
                        o.call(null, {errors: l.errors.UNKNOWN_ERROR}, null)
                    }), d.open(t, e, !0), d.timeout = null == s ? 6e4 : s, p && "POST" === t && d.setRequestHeader("Content-Type", "application/json"), setTimeout(function () {
                        d.send(u(t, n))
                    }, 0)
                }

                var a = t("../util"), u = t("./prep-body"), c = t("./parse-body"), l = t("../constants"), p = n.XMLHttpRequest && "withCredentials"in new n.XMLHttpRequest;
                e.exports = {get: i, post: o}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {"../constants": 4, "../util": 20, "./parse-body": 16, "./prep-body": 17}],
        13: [function (t, e) {
            "use strict";
            function n(t) {
                var e = o.getUserAgent(), n = !(o.isHTTP() && /(MSIE\s(8|9))|(Phantom)/.test(e));
                return t = t || {}, t.enableCORS && n ? i : r
            }

            var r = t("./jsonp-driver"), i = t("./ajax-driver"), o = t("../util");
            e.exports = n
        }, {"../util": 20, "./ajax-driver": 12, "./jsonp-driver": 14}],
        14: [function (t, e) {
            "use strict";
            function n(t, e) {
                return t.status >= 400 ? [t, null] : [null, e(t)]
            }

            function r() {
            }

            function i(t, e, i, o, s, a) {
                var l;
                s = s || r, null == a && (a = 6e4), l = o(t, e, function (t, e) {
                    c[e] && (clearTimeout(c[e]), s.apply(null, n(t, function (t) {
                        return i(t)
                    })))
                }), "number" == typeof a ? c[l] = setTimeout(function () {
                    c[l] = null, s.apply(null, [{errors: u.errors.UNKNOWN_ERROR}, null])
                }, a) : s.apply(null, [{errors: u.errors.INVALID_TIMEOUT}, null])
            }

            function o(t, e, n, r, o) {
                e._method = "POST", i(t, e, n, a.get, r, o)
            }

            function s(t, e, n, r, o) {
                i(t, e, n, a.get, r, o)
            }

            var a = t("./jsonp"), u = t("../constants"), c = [];
            e.exports = {get: s, post: o}
        }, {"../constants": 4, "./jsonp": 15}],
        15: [function (t, e) {
            (function (n) {
                "use strict";
                function r(t, e) {
                    var n = document.createElement("script"), r = !1;
                    n.src = t, n.async = !0;
                    var i = e || c.error;
                    "function" == typeof i && (n.onerror = function (e) {
                        i({url: t, event: e})
                    }), n.onload = n.onreadystatechange = function () {
                        r || this.readyState && "loaded" !== this.readyState && "complete" !== this.readyState || (r = !0, n.onload = n.onreadystatechange = null, n && n.parentNode && n.parentNode.removeChild(n))
                    }, s || (s = document.getElementsByTagName("head")[0]), s.appendChild(n)
                }

                function i(t, e, n, i) {
                    var o, s;
                    return i = i || c.callbackName || "callback", s = i + "_json" + a.generateUUID(), e[i] = s, o = a.createURLParams(t, e), u[s] = function (t) {
                        n(t, s);
                        try {
                            delete u[s]
                        } catch (e) {
                        }
                        u[s] = null
                    }, r(o), s
                }

                function o(t) {
                    c = t
                }

                var s, a = t("../util"), u = n, c = {};
                e.exports = {get: i, init: o}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {"../util": 20}],
        16: [function (t, e) {
            "use strict";
            e.exports = function (t) {
                try {
                    t = JSON.parse(t)
                } catch (e) {
                }
                return t
            }
        }, {}],
        17: [function (t, e) {
            "use strict";
            var n = t("lodash.isstring");
            e.exports = function (t, e) {
                if (!n(t))throw new Error("Method must be a string");
                return "get" !== t.toLowerCase() && null != e && (e = n(e) ? e : JSON.stringify(e)), e
            }
        }, {"lodash.isstring": 49}],
        18: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n = 0, r = ["accountHolderName", "bic", "longFormURL", "mandateReferenceNumber", "maskedIBAN", "shortForm"];
                for (n = 0; n < r.length; n++)e = r[n], this[e] = t[e]
            }

            e.exports = n
        }, {}],
        19: [function (t, e) {
            "use strict";
            e.exports = function (t) {
                return null != t.enableCORS ? t.enableCORS : t.merchantConfiguration ? t.merchantConfiguration.enableCORS : !1
            }
        }, {}],
        20: [function (t, e) {
            (function (n) {
                "use strict";
                function r(t) {
                    var e, n, r = [];
                    for (n = 0; n < t.length; n++)e = t[n], "/" === e.charAt(e.length - 1) && (e = e.substring(0, e.length - 1)), "/" === e.charAt(0) && (e = e.substring(1)), r.push(e);
                    return r.join("/")
                }

                function i(t) {
                    return t && "object" == typeof t && "number" == typeof t.length && "[object Array]" === Object.prototype.toString.call(t) || !1
                }

                function o() {
                    return "xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx".replace(/[xy]/g, function (t) {
                        var e = Math.floor(16 * Math.random()), n = "x" === t ? e : 3 & e | 8;
                        return n.toString(16)
                    })
                }

                function s(t, e) {
                    var n, r = {};
                    for (n in t)t.hasOwnProperty(n) && (r[n] = t[n]);
                    for (n in e)e.hasOwnProperty(n) && (r[n] = e[n]);
                    return r
                }

                function a(t, e) {
                    var n, r, o, s = [];
                    for (o in t)t.hasOwnProperty(o) && (r = t[o], n = e ? i(t) ? e + "[]" : e + "[" + o + "]" : o, s.push("object" == typeof r ? a(r, n) : encodeURIComponent(n) + "=" + encodeURIComponent(r)));
                    return s.join("&")
                }

                function u(t, e) {
                    return t = t || "", !p(e) && h(e) && (t += -1 === t.indexOf("?") ? "?" : "", t += -1 !== t.indexOf("=") ? "&" : "", t += a(e)), t
                }

                function c() {
                    return n.navigator.userAgent
                }

                function l() {
                    return "http:" === n.location.protocol
                }

                var p = t("lodash.isempty"), h = t("lodash.isobject");
                e.exports = {
                    joinUrlFragments: r,
                    isArray: i,
                    generateUUID: o,
                    mergeOptions: s,
                    stringify: a,
                    createURLParams: u,
                    getUserAgent: c,
                    isHTTP: l
                }
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {"lodash.isempty": 42, "lodash.isobject": 48}],
        21: [function (t, e) {
            "use strict";
            function n(t) {
                return new r(t)
            }

            var r = t("./lib/client"), i = t("./lib/util"), o = t("./lib/parse-client-token"), s = t("./lib/get-configuration");
            e.exports = {Client: r, configure: n, util: i, parseClientToken: o, _getConfiguration: s}
        }, {"./lib/client": 2, "./lib/get-configuration": 7, "./lib/parse-client-token": 9, "./lib/util": 20}],
        22: [function (t, e) {
            "use strict";
            function n(t, e) {
                if (e = e || "[" + t + "] is not a valid DOM Element", t && t.nodeType && 1 === t.nodeType)return t;
                if (t && window.jQuery && (t instanceof jQuery || "jquery"in Object(t)) && 0 !== t.length)return t[0];
                if ("string" == typeof t && document.getElementById(t))return document.getElementById(t);
                throw new Error(e)
            }

            e.exports = {normalizeElement: n}
        }, {}],
        23: [function (t, e) {
            "use strict";
            function n(t, e, n, r) {
                t.addEventListener ? t.addEventListener(e, n, r) : t.attachEvent && t.attachEvent("on" + e, n)
            }

            function r(t, e, n, r) {
                t.removeEventListener ? t.removeEventListener(e, n, r) : t.detachEvent && t.detachEvent("on" + e, n)
            }

            e.exports = {addEventListener: n, removeEventListener: r}
        }, {}],
        24: [function (t, e) {
            "use strict";
            function n(t) {
                return "[object Function]" === i.call(t)
            }

            function r(t, e) {
                return function () {
                    t.apply(e, arguments)
                }
            }

            var i = Object.prototype.toString;
            e.exports = {bind: r, isFunction: n}
        }, {}],
        25: [function (t, e) {
            "use strict";
            function n() {
                return "https:" === window.location.protocol
            }

            function r(t) {
                switch (t) {
                    case null:
                    case void 0:
                        return "";
                    case!0:
                        return "1";
                    case!1:
                        return "0";
                    default:
                        return encodeURIComponent(t)
                }
            }

            function i(t, e) {
                var n, o, s = [];
                for (o in t)if (t.hasOwnProperty(o)) {
                    var a = t[o];
                    n = e ? e + "[" + o + "]" : o, "object" == typeof a ? s.push(i(a, n)) : void 0 !== a && null !== a && s.push(r(n) + "=" + r(a))
                }
                return s.join("&")
            }

            function o(t) {
                for (var e = {}, n = t.split("&"), r = 0; r < n.length; r++) {
                    var i = n[r].split("="), o = i[0], s = decodeURIComponent(i[1]);
                    e[o] = s
                }
                return e
            }

            function s(t) {
                var e = t.split("?");
                return 2 !== e.length ? {} : o(e[1])
            }

            e.exports = {isBrowserHttps: n, makeQueryString: i, decodeQueryString: o, getParams: s}
        }, {}],
        26: [function (t, e) {
            var n = t("./lib/dom"), r = t("./lib/url"), i = t("./lib/fn"), o = t("./lib/events");
            e.exports = {
                normalizeElement: n.normalizeElement,
                isBrowserHttps: r.isBrowserHttps,
                makeQueryString: r.makeQueryString,
                decodeQueryString: r.decodeQueryString,
                getParams: r.getParams,
                removeEventListener: o.removeEventListener,
                addEventListener: o.addEventListener,
                bind: i.bind,
                isFunction: i.isFunction
            }
        }, {"./lib/dom": 22, "./lib/events": 23, "./lib/fn": 24, "./lib/url": 25}],
        27: [function (t, e) {
            "use strict";
            function n(t, e) {
                var n = window.getComputedStyle ? getComputedStyle(t) : t.currentStyle;
                return n[e]
            }

            function r() {
                return {
                    html: {height: o.style.height || "", overflow: n(o, "overflow"), position: n(o, "position")},
                    body: {height: s.style.height || "", overflow: n(s, "overflow")}
                }
            }

            function i(t, e) {
                this.assetsUrl = t, this.container = e || document.body, this.iframe = null, o = document.documentElement, s = document.body, this.merchantPageDefaultStyles = r()
            }

            var o, s, a = t("braintree-utilities"), u = t("../shared/receiver"), c = "1.3.0";
            i.prototype.get = function (t, e) {
                var n = this, r = this.constructAuthorizationURL(t);
                this.container && a.isFunction(this.container) ? this.container(r + "&no_style=1") : this.insertIframe(r), new u(function (t) {
                    a.isFunction(n.container) || n.removeIframe(), e(t)
                })
            }, i.prototype.removeIframe = function () {
                this.container && this.container.nodeType && 1 === this.container.nodeType ? this.container.removeChild(this.iframe) : this.container && window.jQuery && this.container instanceof jQuery ? $(this.iframe, this.container).remove() : "string" == typeof this.container && document.getElementById(this.container).removeChild(this.iframe), this.unlockMerchantWindowSize()
            }, i.prototype.insertIframe = function (t) {
                var e = document.createElement("iframe");
                if (e.src = t, this.applyStyles(e), this.lockMerchantWindowSize(), this.container && this.container.nodeType && 1 === this.container.nodeType)this.container.appendChild(e); else if (this.container && window.jQuery && this.container instanceof jQuery && 0 !== this.container.length)this.container.append(e); else {
                    if ("string" != typeof this.container || !document.getElementById(this.container))throw new Error("Unable to find valid container for iframe.");
                    document.getElementById(this.container).appendChild(e)
                }
                this.iframe = e
            }, i.prototype.applyStyles = function (t) {
                t.style.position = "fixed", t.style.top = "0", t.style.left = "0", t.style.height = "100%", t.style.width = "100%", t.setAttribute("frameborder", "0"), t.setAttribute("allowTransparency", "true"), t.style.border = "0", t.style.zIndex = "99999"
            }, i.prototype.lockMerchantWindowSize = function () {
                o.style.overflow = "hidden", s.style.overflow = "hidden", s.style.height = "100%"
            }, i.prototype.unlockMerchantWindowSize = function () {
                var t = this.merchantPageDefaultStyles;
                s.style.height = t.body.height, s.style.overflow = t.body.overflow, o.style.overflow = t.html.overflow
            }, i.prototype.constructAuthorizationURL = function (t) {
                var e, n = window.location.href;
                return n.indexOf("#") > -1 && (n = n.split("#")[0]), e = a.makeQueryString({
                    acsUrl: t.acsUrl,
                    pareq: t.pareq,
                    termUrl: t.termUrl + "&three_d_secure_version=" + c,
                    md: t.md,
                    parentUrl: n
                }), this.assetsUrl + "/3ds/" + c + "/html/style_frame?" + e
            }, e.exports = i
        }, {"../shared/receiver": 34, "braintree-utilities": 26}],
        28: [function (t, e) {
            "use strict";
            function n() {
            }

            function r(t, e) {
                e = e || {}, this.clientToken = e.clientToken, this.container = e.container, this.api = t, this.nonce = null, this._loader = null, this._boundHandleUserClose = i.bind(this._handleUserClose, this)
            }

            var i = t("braintree-utilities"), o = t("./authorization_service"), s = t("./loader");
            r.prototype.verify = function (t, e) {
                if (!i.isFunction(e))throw this.api.sendAnalyticsEvents("3ds.web.no_callback"), new Error("No suitable callback argument was given");
                i.isFunction(t.onUserClose) && (this._onUserClose = t.onUserClose), i.isFunction(t.onLookupComplete) && (this._onLookupComplete = t.onLookupComplete), (void 0 === t.useDefaultLoader || t.useDefaultLoader === !0) && this._createDefaultLoader();
                var n = {nonce: "", amount: t.amount}, r = t.creditCard;
                if ("string" == typeof r)n.nonce = r, this.api.sendAnalyticsEvents("3ds.web.verify.nonce"), this.startVerification(n, e); else {
                    var o = this, s = function (t, r) {
                        return t ? (o._removeDefaultLoader(), e(t)) : (n.nonce = r, void o.startVerification(n, e))
                    };
                    this.api.sendAnalyticsEvents("3ds.web.verify.credit_card"), this.api.tokenizeCard(r, s)
                }
            }, r.prototype.startVerification = function (t, e) {
                this.api.lookup3DS(t, i.bind(this.handleLookupResponse(e), this))
            }, r.prototype.handleLookupResponse = function (t) {
                var e = this;
                return function (n, r) {
                    var s;
                    this._onLookupComplete(), n ? t(n.error) : r.lookup && r.lookup.acsUrl && r.lookup.acsUrl.length > 0 ? (e.nonce = r.paymentMethod.nonce, s = new o(this.clientToken.assetsUrl, this.container), s.get(r.lookup, i.bind(this.handleAuthenticationResponse(t), this)), this._detachListeners(), this._attachListeners()) : (e.nonce = r.paymentMethod.nonce, t(null, {
                        nonce: e.nonce,
                        verificationDetails: r.threeDSecureInfo
                    }))
                }
            }, r.prototype.handleAuthenticationResponse = function (t) {
                return function (e) {
                    var n, r = i.decodeQueryString(e);
                    r.user_closed || (n = JSON.parse(r.auth_response), n.success ? t(null, {
                        nonce: n.paymentMethod.nonce,
                        verificationDetails: n.threeDSecureInfo
                    }) : n.threeDSecureInfo && n.threeDSecureInfo.liabilityShiftPossible ? t(null, {
                        nonce: this.nonce,
                        verificationDetails: n.threeDSecureInfo
                    }) : t(n.error))
                }
            }, r.prototype._attachListeners = function () {
                i.addEventListener(window, "message", this._boundHandleUserClose)
            }, r.prototype._detachListeners = function () {
                i.removeEventListener(window, "message", this._boundHandleUserClose)
            }, r.prototype._createDefaultLoader = function () {
                this._loader = new s, document.body.appendChild(this._loader.getElement())
            }, r.prototype._removeDefaultLoader = function () {
                if (this._loader) {
                    var t = this._loader.getElement(), e = t.parentNode;
                    e && e.removeChild(t), this._loader.dispose(), this._loader = null
                }
            }, r.prototype._handleUserClose = function (t) {
                "user_closed=true" === t.data && this._onUserClose()
            }, r.prototype._onUserClose = n, r.prototype._onLookupComplete = function () {
                this._removeDefaultLoader()
            }, e.exports = r
        }, {"./authorization_service": 27, "./loader": 30, "braintree-utilities": 26}],
        29: [function (t, e) {
            "use strict";
            var n = t("./client");
            e.exports = {
                create: function (t, e) {
                    var r = new n(t, e);
                    return r
                }
            }
        }, {"./client": 28}],
        30: [function (t, e) {
            "use strict";
            function n() {
                this._element = document.createElement("div"), this._element.style.cssText = this._cssDeclarations, this._display = null, this._initialize()
            }

            var r = t("./loader_display"), i = t("./loader_message"), o = t("./loader_spinner");
            n.prototype._cssDeclarations = ["filter:progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#7F000000, EndColorStr=#7F000000)", "background-color: rgba(0, 0, 0, 0.5)", "display: table", "height: 100%", "left: 0", "position: fixed", "right: 0", "top: 0", "width: 100%", "z-index: 99999"].join(";"), n.prototype.getElement = function () {
                return this._element
            }, n.prototype.dispose = function () {
                this._display.dispose(), this._display = null, this._element = null
            }, n.prototype._initialize = function () {
                var t = new o, e = window.SVGElement && window.SVGAnimateElement && window.SVGAnimateTransformElement;
                e || (t = new i("Loading...")), this._display = new r(t), this.getElement().appendChild(this._display.getElement())
            }, e.exports = n
        }, {"./loader_display": 31, "./loader_message": 32, "./loader_spinner": 33}],
        31: [function (t, e) {
            "use strict";
            function n(t) {
                this._element = document.createElement("div"), this._element.style.cssText = this._cssDeclarations, this._displayObject = t, this._initialize()
            }

            n.prototype._cssDeclarations = ["display: table-cell", "vertical-align: middle"].join(";"), n.prototype.getElement = function () {
                return this._element
            }, n.prototype.dispose = function () {
                this._displayObject.dispose(), this._displayObject = null, this._element = null
            }, n.prototype._initialize = function () {
                this.getElement().appendChild(this._displayObject.getElement())
            }, e.exports = n
        }, {}],
        32: [function (t, e) {
            "use strict";
            function n(t) {
                this._element = document.createElement("div"), this._element.style.cssText = this._cssDeclarations, this._element.innerHTML = t
            }

            n.prototype._cssDeclarations = ["color: #fff", "font-family: Helvetica, sans-serif", "font-size: 12px", "text-align: center"].join(";"), n.prototype.getElement = function () {
                return this._element
            }, n.prototype.dispose = function () {
                this._element = null
            }, e.exports = n
        }, {}],
        33: [function (t, e) {
            "use strict";
            function n() {
                this._element = document.createElement("div"), this._element.style.cssText = this._cssDeclarations, this._element.innerHTML = this._markup
            }

            n.prototype._cssDeclarations = ["height: 36px", "margin-left: auto", "margin-right: auto", "width: 36px"].join(";"), n.prototype._markup = ['<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"', 'width="100%" height="100%" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">', '  <path fill="#FFF" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">', '    <animateTransform attributeType="xml"', '    attributeName="transform"', '    type="rotate"', '    from="0 25 25"', '    to="360 25 25"', '    dur="780ms"', '    repeatCount="indefinite"', '    calcMode="spline"', '    keySplines="0.44, 0.22, 0, 1"', '    keyTimes="0;1"/>', "  </path>", "</svg>"].join(""), n.prototype.getElement = function () {
                return this._element
            }, n.prototype.dispose = function () {
                this._element = null
            }, e.exports = n
        }, {}],
        34: [function (t, e) {
            "use strict";
            function n(t) {
                this.postMessageReceiver(t), this.hashChangeReceiver(t)
            }

            var r = t("braintree-utilities");
            n.prototype.postMessageReceiver = function (t) {
                var e = this;
                this.wrappedCallback = function (n) {
                    var r = n.data;
                    (/^(auth_response=)/.test(r) || "user_closed=true" === r) && (t(r), e.stopListening())
                }, r.addEventListener(window, "message", this.wrappedCallback)
            }, n.prototype.hashChangeReceiver = function (t) {
                var e, n = window.location.hash, r = this;
                this.poll = setInterval(function () {
                    e = window.location.hash, e.length > 0 && e !== n && (r.stopListening(), e = e.substring(1, e.length), t(e), window.location.hash = n.length > 0 ? n : "")
                }, 10)
            }, n.prototype.stopListening = function () {
                clearTimeout(this.poll), r.removeEventListener(window, "message", this.wrappedCallback)
            }, e.exports = n
        }, {"braintree-utilities": 26}],
        35: [function (t, e) {
            "use strict";
            var n, r = Array.prototype.indexOf;
            n = r ? function (t, e) {
                return t.indexOf(e)
            } : function (t, e) {
                for (var n = 0, r = t.length; r > n; n++)if (t[n] === e)return n;
                return -1
            }, e.exports = {indexOf: n}
        }, {}],
        36: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        37: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        38: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        39: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n, r, i, o = [{min: 0, max: 180, chars: 7}, {min: 181, max: 620, chars: 14}, {
                    min: 621,
                    max: 960,
                    chars: 22
                }];
                for (i = o.length, t = t || window.innerWidth, n = 0; i > n; n++)r = o[n], t >= r.min && t <= r.max && (e = r.chars);
                return e || 60
            }

            function r(t, e) {
                var n, r;
                return -1 === t.indexOf("@") ? t : (t = t.split("@"), n = t[0], r = t[1], n.length > e && (n = n.slice(0, e) + "..."), r.length > e && (r = "..." + r.slice(-e)), n + "@" + r)
            }

            e.exports = {truncateEmail: r, getMaxCharLength: n}
        }, {}],
        40: [function (t, e) {
            "use strict";
            function n() {
                return "https:" === window.location.protocol
            }

            function r(t) {
                switch (t) {
                    case null:
                    case void 0:
                        return "";
                    case!0:
                        return "1";
                    case!1:
                        return "0";
                    default:
                        return encodeURIComponent(t)
                }
            }

            function i(t, e) {
                var n, o, s = [];
                for (o in t)if (t.hasOwnProperty(o)) {
                    var a = t[o];
                    n = e ? e + "[" + o + "]" : o, "object" == typeof a ? s.push(i(a, n)) : void 0 !== a && null !== a && s.push(r(n) + "=" + r(a))
                }
                return s.join("&")
            }

            function o(t) {
                for (var e = {}, n = t.split("&"), r = 0; r < n.length; r++) {
                    var i = n[r].split("="), o = i[0], s = decodeURIComponent(i[1]);
                    e[o] = s
                }
                return e
            }

            function s(t) {
                var e = t.split("?");
                return 2 !== e.length ? {} : o(e[1])
            }

            function a(t) {
                if (t = t.toLowerCase(), !/^http/.test(t))return !1;
                c.href = t;
                var e = c.hostname.split("."), n = e.slice(-2).join(".");
                return -1 === u.indexOf(l, n) ? !1 : !0
            }

            var u = t("./array"), c = document.createElement("a"), l = ["paypal.com", "braintreepayments.com", "braintreegateway.com", "localhost"];
            e.exports = {
                isBrowserHttps: n,
                makeQueryString: i,
                decodeQueryString: o,
                getParams: s,
                isWhitelistedDomain: a
            }
        }, {"./array": 35}],
        41: [function (t, e) {
            var n = t("./lib/dom"), r = t("./lib/url"), i = t("./lib/fn"), o = t("./lib/events"), s = t("./lib/string"), a = t("./lib/array");
            e.exports = {
                string: s,
                array: a,
                normalizeElement: n.normalizeElement,
                isBrowserHttps: r.isBrowserHttps,
                makeQueryString: r.makeQueryString,
                decodeQueryString: r.decodeQueryString,
                getParams: r.getParams,
                isWhitelistedDomain: r.isWhitelistedDomain,
                removeEventListener: o.removeEventListener,
                addEventListener: o.addEventListener,
                bind: i.bind,
                isFunction: i.isFunction
            }
        }, {
            "./lib/array": 35,
            "./lib/dom": 36,
            "./lib/events": 37,
            "./lib/fn": 38,
            "./lib/string": 39,
            "./lib/url": 40
        }],
        42: [function (t, e) {
            function n(t) {
                return !!t && "object" == typeof t
            }

            function r(t) {
                return function (e) {
                    return null == e ? void 0 : e[t]
                }
            }

            function i(t) {
                return null != t && o(d(t))
            }

            function o(t) {
                return "number" == typeof t && t > -1 && t % 1 == 0 && h >= t
            }

            function s(t) {
                return null == t ? !0 : i(t) && (u(t) || l(t) || a(t) || n(t) && c(t.splice)) ? !t.length : !p(t).length
            }

            var a = t("lodash.isarguments"), u = t("lodash.isarray"), c = t("lodash.isfunction"), l = t("lodash.isstring"), p = t("lodash.keys"), h = 9007199254740991, d = r("length");
            e.exports = s
        }, {
            "lodash.isarguments": 43,
            "lodash.isarray": 44,
            "lodash.isfunction": 45,
            "lodash.isstring": 49,
            "lodash.keys": 46
        }],
        43: [function (t, e) {
            function n(t) {
                return !!t && "object" == typeof t
            }

            function r(t) {
                return function (e) {
                    return null == e ? void 0 : e[t]
                }
            }

            function i(t) {
                return null != t && o(p(t))
            }

            function o(t) {
                return "number" == typeof t && t > -1 && t % 1 == 0 && l >= t
            }

            function s(t) {
                return n(t) && i(t) && u.call(t, "callee") && !c.call(t, "callee")
            }

            var a = Object.prototype, u = a.hasOwnProperty, c = a.propertyIsEnumerable, l = 9007199254740991, p = r("length");
            e.exports = s
        }, {}],
        44: [function (t, e) {
            function n(t) {
                return !!t && "object" == typeof t
            }

            function r(t, e) {
                var n = null == t ? void 0 : t[e];
                return a(n) ? n : void 0
            }

            function i(t) {
                return "number" == typeof t && t > -1 && t % 1 == 0 && y >= t
            }

            function o(t) {
                return s(t) && f.call(t) == c
            }

            function s(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            function a(t) {
                return null == t ? !1 : o(t) ? m.test(h.call(t)) : n(t) && l.test(t)
            }

            var u = "[object Array]", c = "[object Function]", l = /^\[object .+?Constructor\]$/, p = Object.prototype, h = Function.prototype.toString, d = p.hasOwnProperty, f = p.toString, m = RegExp("^" + h.call(d).replace(/[\\^$.*+?()[\]{}|]/g, "\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g, "$1.*?") + "$"), g = r(Array, "isArray"), y = 9007199254740991, b = g || function (t) {
                    return n(t) && i(t.length) && f.call(t) == u
                };
            e.exports = b
        }, {}],
        45: [function (t, e) {
            function n(t) {
                return r(t) && s.call(t) == i
            }

            function r(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            var i = "[object Function]", o = Object.prototype, s = o.toString;
            e.exports = n
        }, {}],
        46: [function (t, e) {
            function n(t) {
                return function (e) {
                    return null == e ? void 0 : e[t]
                }
            }

            function r(t) {
                return null != t && o(y(t))
            }

            function i(t, e) {
                return t = "number" == typeof t || h.test(t) ? +t : -1, e = null == e ? g : e, t > -1 && t % 1 == 0 && e > t
            }

            function o(t) {
                return "number" == typeof t && t > -1 && t % 1 == 0 && g >= t
            }

            function s(t) {
                for (var e = u(t), n = e.length, r = n && t.length, s = !!r && o(r) && (p(t) || l(t)), a = -1, c = []; ++a < n;) {
                    var h = e[a];
                    (s && i(h, r) || f.call(t, h)) && c.push(h)
                }
                return c
            }

            function a(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            function u(t) {
                if (null == t)return [];
                a(t) || (t = Object(t));
                var e = t.length;
                e = e && o(e) && (p(t) || l(t)) && e || 0;
                for (var n = t.constructor, r = -1, s = "function" == typeof n && n.prototype === t, u = Array(e), c = e > 0; ++r < e;)u[r] = r + "";
                for (var h in t)c && i(h, e) || "constructor" == h && (s || !f.call(t, h)) || u.push(h);
                return u
            }

            var c = t("lodash._getnative"), l = t("lodash.isarguments"), p = t("lodash.isarray"), h = /^\d+$/, d = Object.prototype, f = d.hasOwnProperty, m = c(Object, "keys"), g = 9007199254740991, y = n("length"), b = m ? function (t) {
                var e = null == t ? void 0 : t.constructor;
                return "function" == typeof e && e.prototype === t || "function" != typeof t && r(t) ? s(t) : a(t) ? m(t) : []
            } : s;
            e.exports = b
        }, {"lodash._getnative": 47, "lodash.isarguments": 43, "lodash.isarray": 44}],
        47: [function (t, e) {
            function n(t) {
                return !!t && "object" == typeof t
            }

            function r(t, e) {
                var n = null == t ? void 0 : t[e];
                return s(n) ? n : void 0
            }

            function i(t) {
                return o(t) && h.call(t) == a
            }

            function o(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            function s(t) {
                return null == t ? !1 : i(t) ? d.test(l.call(t)) : n(t) && u.test(t)
            }

            var a = "[object Function]", u = /^\[object .+?Constructor\]$/, c = Object.prototype, l = Function.prototype.toString, p = c.hasOwnProperty, h = c.toString, d = RegExp("^" + l.call(p).replace(/[\\^$.*+?()[\]{}|]/g, "\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g, "$1.*?") + "$");
            e.exports = r
        }, {}],
        48: [function (t, e) {
            function n(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            e.exports = n
        }, {}],
        49: [function (t, e) {
            function n(t) {
                return !!t && "object" == typeof t
            }

            function r(t) {
                return "string" == typeof t || n(t) && s.call(t) == i
            }

            var i = "[object String]", o = Object.prototype, s = o.toString;
            e.exports = r
        }, {}],
        50: [function (t, e) {
            "use strict";
            var n = t("framebus");
            n.events = t("./lib/events"), e.exports = n
        }, {"./lib/events": 51, framebus: 52}],
        51: [function (t, e) {
            "use strict";
            for (var n = ["PAYMENT_METHOD_REQUEST", "PAYMENT_METHOD_RECEIVED", "PAYMENT_METHOD_GENERATED", "PAYMENT_METHOD_NOT_GENERATED", "PAYMENT_METHOD_CANCELLED", "PAYMENT_METHOD_ERROR", "CONFIGURATION_REQUEST", "ROOT_METADATA_REQUEST", "ERROR", "WARNING", "UI_POPUP_DID_OPEN", "UI_POPUP_DID_CLOSE", "UI_POPUP_FORCE_CLOSE", "ASYNC_DEPENDENCY_INITIALIZING", "ASYNC_DEPENDENCY_READY", "USER_FORM_SUBMIT_REQUEST", "SEND_ANALYTICS_EVENTS"], r = {}, i = 0; i < n.length; i++) {
                var o = n[i];
                r[o] = o
            }
            e.exports = r
        }, {}],
        52: [function (e, n, r) {
            "use strict";
            !function (e, i) {
                "object" == typeof r && "undefined" != typeof n ? n.exports = i() : "function" == typeof t && t.amd ? t([], i) : e.framebus = i()
            }(this, function () {
                function t(t) {
                    return null == t ? !1 : null == t.Window ? !1 : t.constructor !== t.Window ? !1 : (v.push(t), !0)
                }

                function e(t) {
                    var e, n = {};
                    for (e in b)b.hasOwnProperty(e) && (n[e] = b[e]);
                    return n._origin = t || "*", n
                }

                function n(t) {
                    var e, n, r = o(this);
                    return s(t) ? !1 : s(r) ? !1 : (n = Array.prototype.slice.call(arguments, 1), e = a(t, n, r), e === !1 ? !1 : (d(y.top, e, r), !0))
                }

                function r(t, e) {
                    var n = o(this);
                    return g(t, e, n) ? !1 : (_[n] = _[n] || {}, _[n][t] = _[n][t] || [], _[n][t].push(e), !0)
                }

                function i(t, e) {
                    var n, r, i = o(this);
                    if (g(t, e, i))return !1;
                    if (r = _[i] && _[i][t], !r)return !1;
                    for (n = 0; n < r.length; n++)if (r[n] === e)return r.splice(n, 1), !0;
                    return !1
                }

                function o(t) {
                    return t && t._origin || "*"
                }

                function s(t) {
                    return "string" != typeof t
                }

                function a(t, e, n) {
                    var r = !1, i = {event: t, origin: n}, o = e[e.length - 1];
                    "function" == typeof o && (i.reply = m(o, n), e = e.slice(0, -1)), i.args = e;
                    try {
                        r = E + JSON.stringify(i)
                    } catch (s) {
                        throw new Error("Could not stringify event: " + s.message)
                    }
                    return r
                }

                function u(t) {
                    var e, n, r, i;
                    if (t.data.slice(0, E.length) !== E)return !1;
                    try {
                        e = JSON.parse(t.data.slice(E.length))
                    } catch (o) {
                        return !1
                    }
                    return null != e.reply && (n = t.origin, r = t.source, i = e.reply, e.reply = function (t) {
                        var e = a(i, [t], n);
                        return e === !1 ? !1 : void r.postMessage(e, n)
                    }, e.args.push(e.reply)), e
                }

                function c(t) {
                    y || (y = t || window, y.addEventListener ? y.addEventListener("message", p, !1) : y.attachEvent ? y.attachEvent("onmessage", p) : null === y.onmessage ? y.onmessage = p : y = null)
                }

                function l() {
                    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (t) {
                        var e = 16 * Math.random() | 0, n = "x" === t ? e : 3 & e | 8;
                        return n.toString(16)
                    })
                }

                function p(t) {
                    var e;
                    s(t.data) || (e = u(t), e && (h("*", e.event, e.args, t), h(t.origin, e.event, e.args, t), f(t.data, e.origin, t.source)))
                }

                function h(t, e, n, r) {
                    var i;
                    if (_[t] && _[t][e])for (i = 0; i < _[t][e].length; i++)_[t][e][i].apply(r, n)
                }

                function d(t, e, n) {
                    var r;
                    try {
                        t.postMessage(e, n)
                    } catch (i) {
                        return
                    }
                    for (t.opener && !t.opener.closed && t.opener !== y && d(t.opener.top, e, n), r = 0; r < t.frames.length; r++)d(t.frames[r], e, n)
                }

                function f(t, e, n) {
                    var r, i;
                    for (r = v.length - 1; r >= 0; r--)i = v[r], i.closed === !0 ? v = v.slice(r, 1) : n !== i && d(i.top, t, e)
                }

                function m(t, e) {
                    function n(i, o) {
                        t(i, o), b.target(e).unsubscribe(r, n)
                    }

                    var r = l();
                    return b.target(e).subscribe(r, n), r
                }

                function g(t, e, n) {
                    return s(t) ? !0 : "function" != typeof e ? !0 : s(n) ? !0 : !1
                }

                var y, b, v = [], _ = {}, E = "/*framebus*/";
                return c(), b = {
                    target: e,
                    include: t,
                    publish: n,
                    pub: n,
                    trigger: n,
                    emit: n,
                    subscribe: r,
                    sub: r,
                    on: r,
                    unsubscribe: i,
                    unsub: i,
                    off: i
                }
            })
        }, {}],
        53: [function (t, e) {
            "use strict";
            function n(t, e) {
                a.emit(a.events.ERROR, {type: e, message: t})
            }

            function r(t) {
                t = t || {};
                var e = t.coinbase;
                if (null == t.apiClient)n("settings.apiClient is required for coinbase", u); else if (t.configuration.coinbaseEnabled)if (e && (e.container || e.button))if (e.container && e.button)n("options.coinbase.container and options.coinbase.button are mutually exclusive", u); else {
                    if (s.isSupportedBrowser())return !0;
                    n("Coinbase is not supported by your browser. Please consider upgrading", "UNSUPPORTED_BROWSER")
                } else n("Either options.coinbase.container or options.coinbase.button is required for Coinbase integrations", u); else n("Coinbase is not enabled for your merchant account", u);
                return !1
            }

            function i(t) {
                return r(t) ? new o(t) : void 0
            }

            var o = t("./lib/coinbase"), s = t("./lib/detector"), a = t("braintree-bus"), u = "CONFIGURATION";
            e.exports = {create: i}
        }, {"./lib/coinbase": 56, "./lib/detector": 58, "braintree-bus": 64}],
        54: [function (t, e) {
            (function (t) {
                "use strict";
                function n(e) {
                    return e = e || t.navigator.userAgent, /AppleWebKit\//.test(e) && /Mobile\//.test(e) ? e.replace(/.* OS ([0-9_]+) like Mac OS X.*/, "$1").replace(/_/g, ".") : null
                }

                function r(e) {
                    e = e || t.navigator.userAgent;
                    var n = null, r = /MSIE.(\d+)/.exec(e);
                    return /Trident/.test(e) && (n = 11), r && (n = parseInt(r[1], 10)), n
                }

                function i(e) {
                    return e = e || t.navigator.userAgent, /Android/.test(e) ? e.replace(/^.* Android ([0-9\.]+).*$/, "$1") : null
                }

                e.exports = {ieVersion: r, iOSSafariVersion: n, androidVersion: i}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {}],
        55: [function (t, e) {
            "use strict";
            function n(t, e, n) {
                return t ? (r.emit(r.events.ERROR, t.error), void n._sendAnalyticsEvent("generate.nonce.failed")) : (r.emit(r.events.PAYMENT_METHOD_GENERATED, e), void n._sendAnalyticsEvent("generate.nonce.succeeded"))
            }

            var r = t("braintree-bus");
            e.exports = {tokenize: n}
        }, {"braintree-bus": 64}],
        56: [function (t, e) {
            (function (n) {
                "use strict";
                function r(t) {
                    return {
                        clientId: t.configuration.coinbase.clientId,
                        redirectUrl: t.configuration.coinbase.redirectUrl,
                        scopes: t.configuration.coinbase.scopes || c.SCOPES,
                        meta: {authorizations_merchant_account: t.configuration.coinbase.merchantAccount || ""}
                    }
                }

                function i(t) {
                    var e;
                    this.buttonId = t.coinbase.button || c.BUTTON_ID, this.apiClient = t.apiClient, this.assetsUrl = t.configuration.assetsUrl, this.environment = t.configuration.coinbase.environment, this._onOAuthSuccess = o.bind(this._onOAuthSuccess, this), this._handleButtonClick = o.bind(this._handleButtonClick, this), this.popupParams = r(t), this.redirectDoneInterval = null, t.coinbase.container ? (e = o.normalizeElement(t.coinbase.container), this._insertFrame(e)) : (n.braintreeCoinbasePopupCallback = this._onOAuthSuccess, e = document.body, o.addEventListener(e, "click", this._handleButtonClick), this._sendAnalyticsEvent("initialized"))
                }

                var o = t("braintree-utilities"), s = t("./dom/composer"), a = t("./url-composer"), u = t("./callbacks"), c = t("./constants"), l = t("./detector"), p = t("braintree-bus");
                i.prototype._sendAnalyticsEvent = function (t) {
                    var e = this.apiClient.integration + ".web.coinbase.";
                    this.apiClient.sendAnalyticsEvents(e + t)
                }, i.prototype._insertFrame = function (t) {
                    var e = s.createFrame({src: this.assetsUrl + "/coinbase/" + c.VERSION + "/coinbase-frame.html"});
                    p.emit(p.events.ASYNC_DEPENDENCY_INITIALIZING), t.appendChild(e)
                }, i.prototype._onOAuthSuccess = function (t) {
                    return t.code ? (p.emit("coinbase:view:navigate", "loading"), this._sendAnalyticsEvent("popup.authorized"), this.apiClient.tokenizeCoinbase({
                        code: t.code,
                        query: a.getQueryString()
                    }, o.bind(function (t, e) {
                        u.tokenize.apply(null, [t, e, this])
                    }, this)), void this._closePopup()) : (this._sendAnalyticsEvent("popup.denied"), void this._closePopup())
                }, i.prototype._clearPollForRedirectDone = function () {
                    this.redirectDoneInterval && (clearInterval(this.redirectDoneInterval), this.redirectDoneInterval = null)
                }, i.prototype._closePopup = function (t) {
                    t = t || this.popup, l.shouldCloseFromParent() && t.close(), this._popupCleanup()
                }, i.prototype._popupCleanup = function () {
                    this._clearPollForRedirectDone(), p.emit(p.events.UI_POPUP_DID_CLOSE, {source: c.INTEGRATION_NAME})
                }, i.prototype._pollForRedirectDone = function (t) {
                    var e = setInterval(o.bind(function () {
                        var e;
                        if (null == t || t.closed)return this._sendAnalyticsEvent("popup.aborted"), void this._popupCleanup();
                        try {
                            if ("about:blank" === t.location.href)throw new Error("Not finished loading");
                            e = o.decodeQueryString(t.location.search.replace(/^\?/, "")).code
                        } catch (n) {
                            return
                        }
                        this._onOAuthSuccess({code: e})
                    }, this), 100);
                    return this.redirectDoneInterval = e, e
                }, i.prototype._openPopup = function () {
                    var t;
                    this._sendAnalyticsEvent("popup.started"), t = s.createPopup(a.compose(this._getOAuthBaseUrl(), this.popupParams)), t.focus(), this._pollForRedirectDone(t), p.trigger(p.events.UI_POPUP_DID_OPEN, {source: c.INTEGRATION_NAME}), p.on(p.events.UI_POPUP_FORCE_CLOSE, function (e) {
                        e.target === c.INTEGRATION_NAME && t.close()
                    }), this.popup = t
                }, i.prototype._getOAuthBaseUrl = function () {
                    var t;
                    return t = "shared_sandbox" === this.environment ? c.SANDBOX_OAUTH_BASE_URL : c.PRODUCTION_OAUTH_BASE_URL
                }, i.prototype._handleButtonClick = function (t) {
                    for (var e = t.target || t.srcElement; ;) {
                        if (null == e)return;
                        if (e === t.currentTarget)return;
                        if (e.id === this.buttonId)break;
                        e = e.parentNode
                    }
                    t && t.preventDefault ? t.preventDefault() : t.returnValue = !1, this._openPopup()
                }, e.exports = i
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {
            "./callbacks": 55,
            "./constants": 57,
            "./detector": 58,
            "./dom/composer": 60,
            "./url-composer": 63,
            "braintree-bus": 64,
            "braintree-utilities": 73
        }],
        57: [function (t, e) {
            "use strict";
            e.exports = {
                PRODUCTION_OAUTH_BASE_URL: "https://coinbase.com",
                SANDBOX_OAUTH_BASE_URL: "https://sandbox.coinbase.com",
                ORIGIN_URL: "https://www.coinbase.com",
                FRAME_NAME: "braintree-coinbase-frame",
                POPUP_NAME: "coinbase",
                BUTTON_ID: "bt-coinbase-button",
                SCOPES: "send",
                VERSION: "0.2.2",
                INTEGRATION_NAME: "Coinbase"
            }
        }, {}],
        58: [function (t, e) {
            "use strict";
            function n() {
                var t = s.ieVersion();
                return !t || t > 8
            }

            function r() {
                var t = s.androidVersion();
                return null == t ? !1 : /^5/.test(t)
            }

            function i() {
                return !(r() || o())
            }

            function o() {
                var t = s.iOSSafariVersion();
                return null == t ? !1 : /^8\.0/.test(t) || /^8\.1$/.test(t)
            }

            var s = t("./browser");
            e.exports = {
                isSupportedBrowser: n,
                shouldCloseFromParent: i,
                shouldDisplayIOSClose: o,
                shouldDisplayLollipopClose: r
            }
        }, {"./browser": 54}],
        59: [function (t, e) {
            "use strict";
            function n(t) {
                var e = document.createElement("button");
                return t = t || {}, e.id = t.id || "coinbase-button", e.style.backgroundColor = t.backgroundColor || "#EEE", e.style.color = t.color || "#4597C3", e.style.border = t.border || "0", e.style.borderRadius = t.borderRadius || "6px", e.style.padding = t.padding || "12px", e.innerHTML = t.innerHTML || "coinbase", e
            }

            e.exports = {create: n}
        }, {}],
        60: [function (t, e) {
            "use strict";
            var n = t("./popup"), r = t("./button"), i = t("./frame");
            e.exports = {createButton: r.create, createPopup: n.create, createFrame: i.create}
        }, {"./button": 59, "./frame": 61, "./popup": 62}],
        61: [function (t, e) {
            "use strict";
            function n(t) {
                return i({
                    src: t.src,
                    name: r.FRAME_NAME,
                    height: "70px",
                    width: "100%",
                    style: {padding: 0, margin: 0, border: 0, outline: "none"}
                })
            }

            var r = t("../constants"), i = t("iframer");
            e.exports = {create: n}
        }, {"../constants": 57, iframer: 74}],
        62: [function (t, e) {
            (function (n) {
                "use strict";
                function r(t) {
                    var e = [];
                    for (var n in t)t.hasOwnProperty(n) && e.push([n, t[n]].join("="));
                    return e.join(",")
                }

                function i() {
                    var t = 850, e = 600;
                    return r({width: t, height: e, left: (screen.width - t) / 2, top: (screen.height - e) / 4})
                }

                function o(t) {
                    return n.open(t, s.POPUP_NAME, i())
                }

                var s = t("../constants");
                e.exports = {create: o}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {"../constants": 57}],
        63: [function (t, e) {
            "use strict";
            function n() {
                return "version=" + i.VERSION
            }

            function r(t, e) {
                var r = t + "/oauth/authorize?response_type=code", i = e.redirectUrl + "?" + n();
                if (r += "&redirect_uri=" + encodeURIComponent(i), r += "&client_id=" + e.clientId, e.scopes && (r += "&scope=" + encodeURIComponent(e.scopes)), e.meta)for (var o in e.meta)e.meta.hasOwnProperty(o) && (r += "&meta[" + encodeURIComponent(o) + "]=" + encodeURIComponent(e.meta[o]));
                return r
            }

            var i = t("./constants");
            e.exports = {compose: r, getQueryString: n}
        }, {"./constants": 57}],
        64: [function (t, e, n) {
            arguments[4][50][0].apply(n, arguments)
        }, {"./lib/events": 65, dup: 50, framebus: 66}],
        65: [function (t, e, n) {
            arguments[4][51][0].apply(n, arguments)
        }, {dup: 51}],
        66: [function (t, e, n) {
            arguments[4][52][0].apply(n, arguments)
        }, {dup: 52}],
        67: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        68: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        69: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        70: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        71: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        72: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 67, dup: 40}],
        73: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 67,
            "./lib/dom": 68,
            "./lib/events": 69,
            "./lib/fn": 70,
            "./lib/string": 71,
            "./lib/url": 72,
            dup: 41
        }],
        74: [function (t, e) {
            "use strict";
            var n = t("lodash.assign"), r = t("lodash.isstring"), i = t("./lib/default-attributes");
            e.exports = function (t) {
                var e = document.createElement("iframe"), o = n({}, i, t);
                o.style && !r(o.style) && (n(e.style, o.style), delete o.style);
                for (var s in o)o.hasOwnProperty(s) && e.setAttribute(s, o[s]);
                return e.getAttribute("id") || (e.id = e.name), e
            }
        }, {"./lib/default-attributes": 75, "lodash.assign": 76, "lodash.isstring": 87}],
        75: [function (t, e) {
            e.exports = {frameBorder: 0, allowtransparency: !0}
        }, {}],
        76: [function (t, e) {
            function n(t, e, n) {
                for (var r = -1, i = o(e), s = i.length; ++r < s;) {
                    var a = i[r], u = t[a], c = n(u, e[a], a, t, e);
                    (c === c ? c === u : u !== u) && (void 0 !== u || a in t) || (t[a] = c)
                }
                return t
            }

            var r = t("lodash._baseassign"), i = t("lodash._createassigner"), o = t("lodash.keys"), s = i(function (t, e, i) {
                return i ? n(t, e, i) : r(t, e)
            });
            e.exports = s
        }, {"lodash._baseassign": 77, "lodash._createassigner": 79, "lodash.keys": 83}],
        77: [function (t, e) {
            function n(t, e) {
                return null == e ? t : r(e, i(e), t)
            }

            var r = t("lodash._basecopy"), i = t("lodash.keys");
            e.exports = n
        }, {"lodash._basecopy": 78, "lodash.keys": 83}],
        78: [function (t, e) {
            function n(t, e, n) {
                n || (n = {});
                for (var r = -1, i = e.length; ++r < i;) {
                    var o = e[r];
                    n[o] = t[o]
                }
                return n
            }

            e.exports = n
        }, {}],
        79: [function (t, e) {
            function n(t) {
                return o(function (e, n) {
                    var o = -1, s = null == e ? 0 : n.length, a = s > 2 ? n[s - 2] : void 0, u = s > 2 ? n[2] : void 0, c = s > 1 ? n[s - 1] : void 0;
                    for ("function" == typeof a ? (a = r(a, c, 5), s -= 2) : (a = "function" == typeof c ? c : void 0, s -= a ? 1 : 0), u && i(n[0], n[1], u) && (a = 3 > s ? void 0 : a, s = 1); ++o < s;) {
                        var l = n[o];
                        l && t(e, l, a)
                    }
                    return e
                })
            }

            var r = t("lodash._bindcallback"), i = t("lodash._isiterateecall"), o = t("lodash.restparam");
            e.exports = n
        }, {"lodash._bindcallback": 80, "lodash._isiterateecall": 81, "lodash.restparam": 82}],
        80: [function (t, e) {
            function n(t, e, n) {
                if ("function" != typeof t)return r;
                if (void 0 === e)return t;
                switch (n) {
                    case 1:
                        return function (n) {
                            return t.call(e, n)
                        };
                    case 3:
                        return function (n, r, i) {
                            return t.call(e, n, r, i)
                        };
                    case 4:
                        return function (n, r, i, o) {
                            return t.call(e, n, r, i, o)
                        };
                    case 5:
                        return function (n, r, i, o, s) {
                            return t.call(e, n, r, i, o, s)
                        }
                }
                return function () {
                    return t.apply(e, arguments)
                }
            }

            function r(t) {
                return t
            }

            e.exports = n
        }, {}],
        81: [function (t, e) {
            function n(t) {
                return function (e) {
                    return null == e ? void 0 : e[t]
                }
            }

            function r(t) {
                return null != t && s(l(t))
            }

            function i(t, e) {
                return t = "number" == typeof t || u.test(t) ? +t : -1, e = null == e ? c : e, t > -1 && t % 1 == 0 && e > t
            }

            function o(t, e, n) {
                if (!a(n))return !1;
                var o = typeof e;
                if ("number" == o ? r(n) && i(e, n.length) : "string" == o && e in n) {
                    var s = n[e];
                    return t === t ? t === s : s !== s
                }
                return !1
            }

            function s(t) {
                return "number" == typeof t && t > -1 && t % 1 == 0 && c >= t
            }

            function a(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            var u = /^\d+$/, c = 9007199254740991, l = n("length");
            e.exports = o
        }, {}],
        82: [function (t, e) {
            function n(t, e) {
                if ("function" != typeof t)throw new TypeError(r);
                return e = i(void 0 === e ? t.length - 1 : +e || 0, 0), function () {
                    for (var n = arguments, r = -1, o = i(n.length - e, 0), s = Array(o); ++r < o;)s[r] = n[e + r];
                    switch (e) {
                        case 0:
                            return t.call(this, s);
                        case 1:
                            return t.call(this, n[0], s);
                        case 2:
                            return t.call(this, n[0], n[1], s)
                    }
                    var a = Array(e + 1);
                    for (r = -1; ++r < e;)a[r] = n[r];
                    return a[e] = s, t.apply(this, a)
                }
            }

            var r = "Expected a function", i = Math.max;
            e.exports = n
        }, {}],
        83: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 84, "lodash.isarguments": 85, "lodash.isarray": 86}],
        84: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        85: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        86: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        87: [function (t, e, n) {
            arguments[4][49][0].apply(n, arguments)
        }, {dup: 49}],
        88: [function (t, e, n) {
            arguments[4][2][0].apply(n, arguments)
        }, {
            "./coinbase-account": 89,
            "./credit-card": 91,
            "./europe-bank-account": 92,
            "./normalize-api-fields": 94,
            "./parse-client-token": 95,
            "./paypal-account": 96,
            "./request/choose-driver": 99,
            "./sepa-mandate": 104,
            "./should-enable-cors": 105,
            "./util": 106,
            "braintree-3ds": 115,
            "braintree-utilities": 127,
            dup: 2
        }],
        89: [function (t, e, n) {
            arguments[4][3][0].apply(n, arguments)
        }, {dup: 3}],
        90: [function (t, e, n) {
            arguments[4][4][0].apply(n, arguments)
        }, {dup: 4}],
        91: [function (t, e, n) {
            arguments[4][5][0].apply(n, arguments)
        }, {dup: 5}],
        92: [function (t, e, n) {
            arguments[4][6][0].apply(n, arguments)
        }, {dup: 6}],
        93: [function (t, e, n) {
            arguments[4][7][0].apply(n, arguments)
        }, {
            "./parse-client-token": 95,
            "./request/choose-driver": 99,
            "./should-enable-cors": 105,
            "./util": 106,
            dup: 7
        }],
        94: [function (t, e, n) {
            arguments[4][8][0].apply(n, arguments)
        }, {dup: 8}],
        95: [function (t, e, n) {
            arguments[4][9][0].apply(n, arguments)
        }, {"./polyfill": 97, "braintree-utilities": 127, dup: 9}],
        96: [function (t, e, n) {
            arguments[4][10][0].apply(n, arguments)
        }, {dup: 10}],
        97: [function (t, e, n) {
            arguments[4][11][0].apply(n, arguments)
        }, {dup: 11}],
        98: [function (t, e, n) {
            arguments[4][12][0].apply(n, arguments)
        }, {"../constants": 90, "../util": 106, "./parse-body": 102, "./prep-body": 103, dup: 12}],
        99: [function (t, e, n) {
            arguments[4][13][0].apply(n, arguments)
        }, {"../util": 106, "./ajax-driver": 98, "./jsonp-driver": 100, dup: 13}],
        100: [function (t, e, n) {
            arguments[4][14][0].apply(n, arguments)
        }, {"../constants": 90, "./jsonp": 101, dup: 14}],
        101: [function (t, e, n) {
            arguments[4][15][0].apply(n, arguments)
        }, {"../util": 106, dup: 15}],
        102: [function (t, e, n) {
            arguments[4][16][0].apply(n, arguments)
        }, {dup: 16}],
        103: [function (t, e, n) {
            arguments[4][17][0].apply(n, arguments)
        }, {dup: 17, "lodash.isstring": 135}],
        104: [function (t, e, n) {
            arguments[4][18][0].apply(n, arguments)
        }, {dup: 18}],
        105: [function (t, e, n) {
            arguments[4][19][0].apply(n, arguments)
        }, {dup: 19}],
        106: [function (t, e, n) {
            arguments[4][20][0].apply(n, arguments)
        }, {dup: 20, "lodash.isempty": 128, "lodash.isobject": 134}],
        107: [function (t, e, n) {
            arguments[4][21][0].apply(n, arguments)
        }, {
            "./lib/client": 88,
            "./lib/get-configuration": 93,
            "./lib/parse-client-token": 95,
            "./lib/util": 106,
            dup: 21
        }],
        108: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        109: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        110: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        111: [function (t, e, n) {
            arguments[4][25][0].apply(n, arguments)
        }, {dup: 25}],
        112: [function (t, e, n) {
            arguments[4][26][0].apply(n, arguments)
        }, {"./lib/dom": 108, "./lib/events": 109, "./lib/fn": 110, "./lib/url": 111, dup: 26}],
        113: [function (t, e, n) {
            arguments[4][27][0].apply(n, arguments)
        }, {"../shared/receiver": 120, "braintree-utilities": 112, dup: 27}],
        114: [function (t, e, n) {
            arguments[4][28][0].apply(n, arguments)
        }, {"./authorization_service": 113, "./loader": 116, "braintree-utilities": 112, dup: 28}],
        115: [function (t, e, n) {
            arguments[4][29][0].apply(n, arguments)
        }, {"./client": 114, dup: 29}],
        116: [function (t, e, n) {
            arguments[4][30][0].apply(n, arguments)
        }, {"./loader_display": 117, "./loader_message": 118, "./loader_spinner": 119, dup: 30}],
        117: [function (t, e, n) {
            arguments[4][31][0].apply(n, arguments)
        }, {dup: 31}],
        118: [function (t, e, n) {
            arguments[4][32][0].apply(n, arguments)
        }, {dup: 32}],
        119: [function (t, e, n) {
            arguments[4][33][0].apply(n, arguments)
        }, {dup: 33}],
        120: [function (t, e, n) {
            arguments[4][34][0].apply(n, arguments)
        }, {"braintree-utilities": 112, dup: 34}],
        121: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        122: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        123: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        124: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        125: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        126: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 121, dup: 40}],
        127: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 121,
            "./lib/dom": 122,
            "./lib/events": 123,
            "./lib/fn": 124,
            "./lib/string": 125,
            "./lib/url": 126,
            dup: 41
        }],
        128: [function (t, e, n) {
            arguments[4][42][0].apply(n, arguments)
        }, {
            dup: 42,
            "lodash.isarguments": 129,
            "lodash.isarray": 130,
            "lodash.isfunction": 131,
            "lodash.isstring": 135,
            "lodash.keys": 132
        }],
        129: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        130: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        131: [function (t, e, n) {
            arguments[4][45][0].apply(n, arguments)
        }, {dup: 45}],
        132: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 133, "lodash.isarguments": 129, "lodash.isarray": 130}],
        133: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        134: [function (t, e, n) {
            arguments[4][48][0].apply(n, arguments)
        }, {dup: 48}],
        135: [function (t, e, n) {
            arguments[4][49][0].apply(n, arguments)
        }, {dup: 49}],
        136: [function (t, e, n) {
            arguments[4][50][0].apply(n, arguments)
        }, {"./lib/events": 137, dup: 50, framebus: 138}],
        137: [function (t, e, n) {
            arguments[4][51][0].apply(n, arguments)
        }, {dup: 51}],
        138: [function (t, e, n) {
            arguments[4][52][0].apply(n, arguments)
        }, {dup: 52}],
        139: [function (t, e, n) {
            arguments[4][2][0].apply(n, arguments)
        }, {
            "./coinbase-account": 140,
            "./credit-card": 142,
            "./europe-bank-account": 143,
            "./normalize-api-fields": 145,
            "./parse-client-token": 146,
            "./paypal-account": 147,
            "./request/choose-driver": 150,
            "./sepa-mandate": 155,
            "./should-enable-cors": 156,
            "./util": 157,
            "braintree-3ds": 166,
            "braintree-utilities": 178,
            dup: 2
        }],
        140: [function (t, e, n) {
            arguments[4][3][0].apply(n, arguments)
        }, {dup: 3}],
        141: [function (t, e, n) {
            arguments[4][4][0].apply(n, arguments)
        }, {dup: 4}],
        142: [function (t, e, n) {
            arguments[4][5][0].apply(n, arguments)
        }, {dup: 5}],
        143: [function (t, e, n) {
            arguments[4][6][0].apply(n, arguments)
        }, {dup: 6}],
        144: [function (t, e, n) {
            arguments[4][7][0].apply(n, arguments)
        }, {
            "./parse-client-token": 146,
            "./request/choose-driver": 150,
            "./should-enable-cors": 156,
            "./util": 157,
            dup: 7
        }],
        145: [function (t, e, n) {
            arguments[4][8][0].apply(n, arguments)
        }, {dup: 8}],
        146: [function (t, e, n) {
            arguments[4][9][0].apply(n, arguments)
        }, {"./polyfill": 148, "braintree-utilities": 178, dup: 9}],
        147: [function (t, e, n) {
            arguments[4][10][0].apply(n, arguments)
        }, {dup: 10}],
        148: [function (t, e, n) {
            arguments[4][11][0].apply(n, arguments)
        }, {dup: 11}],
        149: [function (t, e, n) {
            arguments[4][12][0].apply(n, arguments)
        }, {"../constants": 141, "../util": 157, "./parse-body": 153, "./prep-body": 154, dup: 12}],
        150: [function (t, e, n) {
            arguments[4][13][0].apply(n, arguments)
        }, {"../util": 157, "./ajax-driver": 149, "./jsonp-driver": 151, dup: 13}],
        151: [function (t, e, n) {
            arguments[4][14][0].apply(n, arguments)
        }, {"../constants": 141, "./jsonp": 152, dup: 14}],
        152: [function (t, e, n) {
            arguments[4][15][0].apply(n, arguments)
        }, {"../util": 157, dup: 15}],
        153: [function (t, e, n) {
            arguments[4][16][0].apply(n, arguments)
        }, {dup: 16}],
        154: [function (t, e, n) {
            arguments[4][17][0].apply(n, arguments)
        }, {dup: 17, "lodash.isstring": 186}],
        155: [function (t, e, n) {
            arguments[4][18][0].apply(n, arguments)
        }, {dup: 18}],
        156: [function (t, e, n) {
            arguments[4][19][0].apply(n, arguments)
        }, {dup: 19}],
        157: [function (t, e, n) {
            arguments[4][20][0].apply(n, arguments)
        }, {dup: 20, "lodash.isempty": 179, "lodash.isobject": 185}],
        158: [function (t, e, n) {
            arguments[4][21][0].apply(n, arguments)
        }, {
            "./lib/client": 139,
            "./lib/get-configuration": 144,
            "./lib/parse-client-token": 146,
            "./lib/util": 157,
            dup: 21
        }],
        159: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        160: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        161: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        162: [function (t, e, n) {
            arguments[4][25][0].apply(n, arguments)
        }, {dup: 25}],
        163: [function (t, e, n) {
            arguments[4][26][0].apply(n, arguments)
        }, {"./lib/dom": 159, "./lib/events": 160, "./lib/fn": 161, "./lib/url": 162, dup: 26}],
        164: [function (t, e, n) {
            arguments[4][27][0].apply(n, arguments)
        }, {"../shared/receiver": 171, "braintree-utilities": 163, dup: 27}],
        165: [function (t, e, n) {
            arguments[4][28][0].apply(n, arguments)
        }, {"./authorization_service": 164, "./loader": 167, "braintree-utilities": 163, dup: 28}],
        166: [function (t, e, n) {
            arguments[4][29][0].apply(n, arguments)
        }, {"./client": 165, dup: 29}],
        167: [function (t, e, n) {
            arguments[4][30][0].apply(n, arguments)
        }, {"./loader_display": 168, "./loader_message": 169, "./loader_spinner": 170, dup: 30}],
        168: [function (t, e, n) {
            arguments[4][31][0].apply(n, arguments)
        }, {dup: 31}],
        169: [function (t, e, n) {
            arguments[4][32][0].apply(n, arguments)
        }, {dup: 32}],
        170: [function (t, e, n) {
            arguments[4][33][0].apply(n, arguments)
        }, {dup: 33}],
        171: [function (t, e, n) {
            arguments[4][34][0].apply(n, arguments)
        }, {"braintree-utilities": 163, dup: 34}],
        172: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        173: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        174: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        175: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        176: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        177: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 172, dup: 40}],
        178: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 172,
            "./lib/dom": 173,
            "./lib/events": 174,
            "./lib/fn": 175,
            "./lib/string": 176,
            "./lib/url": 177,
            dup: 41
        }],
        179: [function (t, e, n) {
            arguments[4][42][0].apply(n, arguments)
        }, {
            dup: 42,
            "lodash.isarguments": 180,
            "lodash.isarray": 181,
            "lodash.isfunction": 182,
            "lodash.isstring": 186,
            "lodash.keys": 183
        }],
        180: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        181: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        182: [function (t, e, n) {
            arguments[4][45][0].apply(n, arguments)
        }, {dup: 45}],
        183: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 184, "lodash.isarguments": 180, "lodash.isarray": 181}],
        184: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        185: [function (t, e, n) {
            arguments[4][48][0].apply(n, arguments)
        }, {dup: 48}],
        186: [function (t, e, n) {
            arguments[4][49][0].apply(n, arguments)
        }, {dup: 49}],
        187: [function (t, e, n) {
            arguments[4][50][0].apply(n, arguments)
        }, {"./lib/events": 188, dup: 50, framebus: 189}],
        188: [function (t, e, n) {
            arguments[4][51][0].apply(n, arguments)
        }, {dup: 51}],
        189: [function (t, e, n) {
            arguments[4][52][0].apply(n, arguments)
        }, {dup: 52}],
        190: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        191: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        192: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        193: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        194: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        195: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 190, dup: 40}],
        196: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 190,
            "./lib/dom": 191,
            "./lib/events": 192,
            "./lib/fn": 193,
            "./lib/string": 194,
            "./lib/url": 195,
            dup: 41
        }],
        197: [function (t, e) {
            function n(t) {
                var e = window.getComputedStyle ? getComputedStyle(t) : t.currentStyle;
                return {overflow: e.overflow || "", height: t.style.height || ""}
            }

            function r() {
                return {
                    html: {node: document.documentElement, styles: n(document.documentElement)},
                    body: {node: document.body, styles: n(document.body)}
                }
            }

            function i(t, e) {
                if (!t)throw new Error('Parameter "clientToken" cannot be null');
                e = e || {}, this._clientToken = o.parseClientToken(t), this._clientOptions = e, this.container = e.container, this.merchantPageDefaultStyles = null, this.paymentMethodNonceInputField = e.paymentMethodNonceInputField, this.frame = null, this.popup = null, this.insertFrameFunction = e.insertFrame, this.onSuccess = e.onSuccess, this.onCancelled = e.onCancelled, this.onUnsupported = e.onUnsupported, this.loggedInView = null, this.loggedOutView = null, this.insertUI = !0
            }

            var o = t("braintree-api"), s = t("braintree-bus"), a = t("braintree-utilities"), u = t("./logged-in-view"), c = t("./logged-out-view"), l = t("./overlay-view"), p = t("../shared/util/browser"), h = t("../shared/util/dom"), d = t("../shared/constants"), f = t("../shared/util/util"), m = t("../shared/get-locale");
            i.prototype.getViewerUrl = function () {
                var t = this._clientToken.paypal.assetsUrl;
                return t + "/pwpp/" + d.VERSION + "/html/braintree-frame.html"
            }, i.prototype.getProxyUrl = function () {
                var t = this._clientToken.paypal.assetsUrl;
                return t + "/pwpp/" + d.VERSION + "/html/proxy-frame.html"
            }, i.prototype.initialize = function () {
                if (!this._clientToken.paypalEnabled)return void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("PayPal is not enabled")));
                if (!this._isBrowserSecure())return void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("unsupported protocol detected")));
                if (this._isHermesCapable()) {
                    if (!this._isHermesSupportedCurrency())return void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("This PayPal integration does not support this currency")));
                    if (!this._isHermesSupportedCountries())return void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("This PayPal integration does not support this locale")));
                    if (!this._isValidAmount())return void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("Amount must be a number")))
                }
                return this._isMisconfiguredUnvettedMerchant() ? void("function" == typeof this.onUnsupported && this.onUnsupported(new Error("Unvetted merchants must provide a payeeEmail, amount, and currency"))) : (this._overrideClientTokenProperties(), p.isProxyFrameRequired() && this._insertProxyFrame(), this._setupDomElements(), this._setupPaymentMethodNonceInputField(), this._setupViews(), void this._createBusSubscribers())
            }, i.prototype._isSupportedOption = function (t, e) {
                for (var n = e.length, r = !1, i = 0; n > i; i++)t.toLowerCase() === e[i].toLowerCase() && (r = !0);
                return r
            }, i.prototype._isHermesSupportedCurrency = function () {
                return this._isSupportedOption(this._clientOptions.currency, d.ARIES_SUPPORTED_CURRENCIES)
            }, i.prototype._isHermesSupportedCountries = function () {
                return this._isSupportedOption(m(this._clientOptions.locale).split("_")[1], d.ARIES_SUPPORTED_COUNTRIES)
            }, i.prototype._isValidAmount = function () {
                var t = parseFloat(this._clientOptions.amount);
                return "number" == typeof t && !isNaN(t) && t >= 0
            }, i.prototype._isMisconfiguredUnvettedMerchant = function () {
                return this._clientToken.paypal.unvettedMerchant && (!this._isHermesCapable() || !this._clientToken.paypal.payeeEmail)
            }, i.prototype._isBrowserSecure = function () {
                return a.isBrowserHttps() || p.isPopupSupported() || this._clientToken.paypal.allowHttp
            }, i.prototype._overrideClientTokenProperties = function () {
                this._clientOptions.displayName && (this._clientToken.paypal.displayName = this._clientOptions.displayName)
            }, i.prototype._setupDomElements = function () {
                this.insertUI && (this.container = a.normalizeElement(this.container))
            }, i.prototype._setupPaymentMethodNonceInputField = function () {
                if (this.insertUI) {
                    var t = this.paymentMethodNonceInputField;
                    a.isFunction(t) || (t = void 0 !== t ? a.normalizeElement(t) : this._createPaymentMethodNonceInputField(), this.paymentMethodNonceInputField = t)
                }
            }, i.prototype._setupViews = function () {
                var t = this._clientToken.paypal.assetsUrl;
                this.insertUI && (this.loggedInView = new u({
                    container: this.container,
                    assetsUrl: t
                }), this.loggedOutView = new c({
                    assetsUrl: t,
                    container: this.container,
                    isCheckout: this._isHermesCapable(),
                    locale: this._clientOptions.locale,
                    merchantId: "merchantId"
                }), a.addEventListener(this.loggedOutView.container, "click", a.bind(this._handleContainerClick, this)), a.addEventListener(this.loggedInView.logoutNode, "click", a.bind(this._handleLogout, this)))
            }, i.prototype._createBusSubscribers = function () {
                s.subscribe("getClientToken", a.bind(this._handleGetClientToken, this)), s.subscribe("getClientOptions", a.bind(this._handleGetClientOptions, this)), s.subscribe("closePayPalModal", a.bind(this._handleCloseMessage, this)), s.subscribe("receivePayPalData", a.bind(this._handleSuccessfulAuthentication, this))
            }, i.prototype._createPaymentMethodNonceInputField = function () {
                var t = document.createElement("input");
                return t.name = "payment_method_nonce", t.type = "hidden", this.container.appendChild(t)
            }, i.prototype._createFrame = function () {
                var t, e = document.createElement("iframe");
                return this._isHermesCapable() ? (t = d.ARIES_FRAME_NAME, e.style.background = "#FFFFFF") : t = d.FRAME_NAME, e.src = this.getViewerUrl(), e.id = t, e.name = t, e.allowTransparency = !0, e.height = "100%", e.width = "100%", e.frameBorder = 0, e.style.position = p.isMobile() ? "absolute" : "fixed", e.style.top = 0, e.style.left = 0, e.style.bottom = 0, e.style.zIndex = 20001, e.style.padding = 0, e.style.margin = 0, e.style.border = 0, e.style.outline = "none", e
            }, i.prototype._removeFrame = function (t) {
                t = t || document.body, this.frame && t.contains(this.frame) && (t.removeChild(this.frame), this._unlockMerchantWindowSize())
            }, i.prototype._insertFrame = function () {
                this.insertFrameFunction ? this.insertFrameFunction(this.getViewerUrl()) : (this.frame = this._createFrame(), document.body.appendChild(this.frame)), this._lockMerchantWindowSize()
            }, i.prototype._handleContainerClick = function (t) {
                function e(t) {
                    return t.className.match(/paypal-button(?!-widget)/) || "braintree-paypal-button" === t.id
                }

                var n = t.target || t.srcElement;
                (e(n) || e(n.parentNode)) && (t.preventDefault ? t.preventDefault() : t.returnValue = !1, this._open())
            }, i.prototype._setMerchantPageDefaultStyles = function () {
                this.merchantPageDefaultStyles = r()
            }, i.prototype._open = function () {
                this._isHermesCapable() && this._addCorrelationIdToClientToken(), p.isPopupSupported() ? this._openPopup() : this._openModal()
            }, i.prototype._close = function () {
                p.isPopupSupported() ? this._closePopup() : this._closeModal()
            }, i.prototype._openModal = function () {
                this._removeFrame(), this._insertFrame()
            }, i.prototype._isHermesCapable = function () {
                return !!this._clientOptions.singleUse && !!this._clientOptions.amount && !!this._clientOptions.currency
            }, i.prototype._openPopup = function () {
                var t, e, n, r = [], i = window.outerWidth || document.documentElement.clientWidth, o = window.outerHeight || document.documentElement.clientHeight, s = "undefined" == typeof window.screenY ? window.screenTop : window.screenY, a = "undefined" == typeof window.screenX ? window.screenLeft : window.screenX;
                this._isHermesCapable() ? (t = d.ARIES_POPUP_NAME, n = d.ARIES_POPUP_HEIGHT, e = d.ARIES_POPUP_WIDTH) : (t = d.POPUP_NAME, n = d.POPUP_HEIGHT, e = d.POPUP_WIDTH);
                var u = (i - e) / 2 + a, c = (o - n) / 2 + s;
                return r.push("height=" + n), r.push("width=" + e), r.push("top=" + c), r.push("left=" + u), r.push(d.POPUP_OPTIONS), this.popup = window.open(this.getViewerUrl(), t, r.join(",")), p.isOverlaySupported() && (this.overlayView = new l(this.popup, this._clientToken.paypal.assetsUrl), this.overlayView.render()), this.popup.focus(), this.popup
            }, i.prototype._addCorrelationIdToClientToken = function () {
                this._clientToken.correlationId = f.generateUid()
            }, i.prototype._createProxyFrame = function () {
                var t = document.createElement("iframe");
                return t.src = this.getProxyUrl(), t.id = d.BRIDGE_FRAME_NAME, t.name = d.BRIDGE_FRAME_NAME, t.allowTransparency = !0, t.height = 0, t.width = 0, t.frameBorder = 0, t.style.position = "static", t.style.padding = 0, t.style.margin = 0, t.style.border = 0, t.style.outline = "none", t
            }, i.prototype._insertProxyFrame = function () {
                this.proxyFrame = this._createProxyFrame(), document.body.appendChild(this.proxyFrame)
            }, i.prototype._closeModal = function () {
                this._removeFrame()
            }, i.prototype._closePopup = function () {
                this.popup && (this.popup.close(), this.popup = null), this.overlayView && p.isOverlaySupported() && this.overlayView.remove()
            }, i.prototype._clientTokenData = function () {
                return {
                    analyticsUrl: this._clientToken.analytics ? this._clientToken.analytics.url : void 0,
                    authorizationFingerprint: this._clientToken.authorizationFingerprint,
                    clientApiUrl: this._clientToken.clientApiUrl,
                    displayName: this._clientToken.paypal.displayName,
                    paypalBaseUrl: this._clientToken.paypal.assetsUrl,
                    paypalClientId: this._clientToken.paypal.clientId,
                    paypalPrivacyUrl: this._clientToken.paypal.privacyUrl,
                    paypalUserAgreementUrl: this._clientToken.paypal.userAgreementUrl,
                    unvettedMerchant: this._clientToken.paypal.unvettedMerchant,
                    payeeEmail: this._clientToken.paypal.payeeEmail,
                    correlationId: this._clientToken.correlationId,
                    offline: this._clientOptions.offline || this._clientToken.paypal.environmentNoNetwork,
                    sdkVersion: this._clientToken.sdkVersion,
                    merchantAppId: this._clientToken.merchantAppId
                }
            }, i.prototype._handleGetClientToken = function (t) {
                t(this._clientTokenData())
            }, i.prototype._clientOptionsData = function () {
                return {
                    locale: this._clientOptions.locale || "en_us",
                    onetime: this._clientOptions.singleUse || !1,
                    integration: this._clientOptions.integration || "paypal",
                    enableShippingAddress: this._clientOptions.enableShippingAddress || !1,
                    enableBillingAddress: this._clientOptions.enableBillingAddress || !1,
                    enableHermes: this._isHermesCapable(),
                    amount: this._clientOptions.amount || null,
                    currency: this._clientOptions.currency || null,
                    shippingAddressOverride: this._clientOptions.shippingAddressOverride || null,
                    enableCORS: this._clientOptions.enableCORS
                }
            }, i.prototype._handleGetClientOptions = function (t) {
                t(this._clientOptionsData())
            }, i.prototype._handleSuccessfulAuthentication = function (t) {
                this._close(), t.type = d.NONCE_TYPE, a.isFunction(this.paymentMethodNonceInputField) ? this.paymentMethodNonceInputField(t.nonce) : (this._showLoggedInContent(t.details.email), this._setNonceInputValue(t.nonce)), a.isFunction(this.onSuccess) && this.onSuccess(t)
            }, i.prototype._lockMerchantWindowSize = function () {
                this._setMerchantPageDefaultStyles(), document.documentElement.style.height = "100%", document.documentElement.style.overflow = "hidden", document.body.style.height = "100%", document.body.style.overflow = "hidden"
            }, i.prototype._unlockMerchantWindowSize = function () {
                this.merchantPageDefaultStyles && (document.documentElement.style.height = this.merchantPageDefaultStyles.html.styles.height, document.documentElement.style.overflow = this.merchantPageDefaultStyles.html.styles.overflow, document.body.style.height = this.merchantPageDefaultStyles.body.styles.height, document.body.style.overflow = this.merchantPageDefaultStyles.body.styles.overflow)
            }, i.prototype._handleCloseMessage = function () {
                this._removeFrame()
            }, i.prototype._showLoggedInContent = function (t) {
                this.loggedOutView.hide(), h.setTextContent(this.loggedInView.emailNode, t), this.loggedInView.show()
            }, i.prototype._handleLogout = function (t) {
                t.preventDefault ? t.preventDefault() : t.returnValue = !1, this.loggedInView.hide(), this.loggedOutView.show(), this._setNonceInputValue(""), a.isFunction(this.onCancelled) && this.onCancelled()
            }, i.prototype._setNonceInputValue = function (t) {
                this.paymentMethodNonceInputField.value = t
            }, e.exports = i
        }, {
            "../shared/constants": 201,
            "../shared/get-locale": 203,
            "../shared/util/browser": 208,
            "../shared/util/dom": 209,
            "../shared/util/util": 210,
            "./logged-in-view": 198,
            "./logged-out-view": 199,
            "./overlay-view": 200,
            "braintree-api": 158,
            "braintree-bus": 187,
            "braintree-utilities": 196
        }],
        198: [function (t, e) {
            function n(t) {
                this.options = t, this.container = this.createViewContainer(), this.createPayPalName(), this.emailNode = this.createEmailNode(), this.logoutNode = this.createLogoutNode()
            }

            var r = t("../shared/constants");
            n.prototype.createViewContainer = function () {
                var t = document.createElement("div");
                t.id = "braintree-paypal-loggedin";
                var e = ["display: none", "max-width: 500px", "overflow: hidden", "padding: 16px", "background-image: url(" + this.options.assetsUrl + "/pwpp/" + r.VERSION + "/images/paypal-small.png)", "background-image: url(" + this.options.assetsUrl + "/pwpp/" + r.VERSION + "/images/paypal-small.svg), none", "background-position: 20px 50%", "background-repeat: no-repeat", "background-size: 13px 15px", "border-top: 1px solid #d1d4d6", "border-bottom: 1px solid #d1d4d6"].join(";");
                return t.style.cssText = e, this.options.container.appendChild(t), t
            }, n.prototype.createPayPalName = function () {
                var t = document.createElement("span");
                t.id = "bt-pp-name", t.innerHTML = "PayPal";
                var e = ["color: #283036", "font-size: 13px", "font-weight: 800", 'font-family: "Helvetica Neue", Helvetica, Arial, sans-serif', "margin-left: 36px", "-webkit-font-smoothing: antialiased", "-moz-font-smoothing: antialiased", "-ms-font-smoothing: antialiased", "font-smoothing: antialiased"].join(";");
                return t.style.cssText = e, this.container.appendChild(t)
            }, n.prototype.createEmailNode = function () {
                var t = document.createElement("span");
                t.id = "bt-pp-email";
                var e = ["color: #6e787f", "font-size: 13px", 'font-family: "Helvetica Neue", Helvetica, Arial, sans-serif', "margin-left: 5px", "-webkit-font-smoothing: antialiased", "-moz-font-smoothing: antialiased", "-ms-font-smoothing: antialiased", "font-smoothing: antialiased"].join(";");
                return t.style.cssText = e, this.container.appendChild(t)
            }, n.prototype.createLogoutNode = function () {
                var t = document.createElement("button");
                t.id = "bt-pp-cancel", t.innerHTML = "Cancel";
                var e = ["color: #3d95ce", "font-size: 11px", 'font-family: "Helvetica Neue", Helvetica, Arial, sans-serif', "line-height: 20px", "margin: 0 0 0 25px", "padding: 0", "background-color: transparent", "border: 0", "cursor: pointer", "text-decoration: underline", "float: right", "-webkit-font-smoothing: antialiased", "-moz-font-smoothing: antialiased", "-ms-font-smoothing: antialiased", "font-smoothing: antialiased"].join(";");
                return t.style.cssText = e, this.container.appendChild(t)
            }, n.prototype.show = function () {
                this.container.style.display = "block"
            }, n.prototype.hide = function () {
                this.container.style.display = "none"
            }, e.exports = n
        }, {"../shared/constants": 201}],
        199: [function (t, e) {
            function n(t) {
                this.options = t, this.assetsUrl = this.options.assetsUrl, this.container = this.createViewContainer(), this.options.isCheckout ? this.createCheckoutWithPayPalButton() : this.createPayWithPayPalButton()
            }

            var r = (t("braintree-utilities"), t("../shared/constants")), i = t("../shared/get-locale");
            n.prototype.createViewContainer = function () {
                var t = document.createElement("div");
                return t.id = "braintree-paypal-loggedout", this.options.container.appendChild(t), t
            }, n.prototype.createPayWithPayPalButton = function () {
                var t = document.createElement("a");
                t.id = "braintree-paypal-button", t.href = "#";
                var e = ["display: block", "width: 115px", "height: 44px", "overflow: hidden"].join(";");
                t.style.cssText = e;
                var n = new Image;
                n.src = this.assetsUrl + "/pwpp/" + r.VERSION + "/images/pay-with-paypal.png", n.setAttribute("alt", "Pay with PayPal");
                var i = ["max-width: 100%", "display: block", "width: 100%", "height: 100%", "outline: none", "border: 0"].join(";");
                n.style.cssText = i, t.appendChild(n), this.container.appendChild(t)
            }, n.prototype.createCheckoutWithPayPalButton = function () {
                var t = document.createElement("script");
                t.src = "//www.paypalobjects.com/api/button.js", t.async = !0, t.setAttribute("data-merchant", this.options.merchantId), t.setAttribute("data-button", "checkout"), t.setAttribute("data-type", "button"), t.setAttribute("data-width", "150"), t.setAttribute("data-height", "44"), t.setAttribute("data-lc", i(this.options.locale)), this.container.appendChild(t)
            }, n.prototype.show = function () {
                this.container.style.display = "block"
            }, n.prototype.hide = function () {
                this.container.style.display = "none"
            }, e.exports = n
        }, {"../shared/constants": 201, "../shared/get-locale": 203, "braintree-utilities": 196}],
        200: [function (t, e) {
            function n(t, e) {
                this.popup = t, this.assetsUrl = e, this.spriteSrc = this.assetsUrl + "/pwpp/" + i.VERSION + "/images/pp_overlay_sprite.png", this._create(), this._setupEvents(), this._pollForPopup()
            }

            var r = t("braintree-utilities"), i = t("../shared/constants");
            n.prototype.render = function () {
                document.body.contains(this.el) || document.body.appendChild(this.el)
            }, n.prototype.remove = function () {
                document.body.contains(this.el) && document.body.removeChild(this.el)
            }, n.prototype._create = function () {
                this.el = document.createElement("div"), this.el.className = "bt-overlay", this._setStyles(this.el, ["z-index: 20001", "position: fixed", "top: 0", "left: 0", "height: 100%", "width: 100%", "text-align: center", "background: #000", "background: rgba(0,0,0,0.7)", '-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=52)"']), this.el.appendChild(this._createCloseIcon()), this.el.appendChild(this._createMessage())
            }, n.prototype._createCloseIcon = function () {
                return this.closeIcon = document.createElement("div"), this.closeIcon.className = "bt-close-overlay", this._setStyles(this.closeIcon, ["position: absolute", "top: 10px", "right: 10px", "cursor: pointer", "background: url(" + this.spriteSrc + ") no-repeat 0 -67px", "height: 14px", "width: 14px"]), this.closeIcon
            }, n.prototype._createMessage = function () {
                var t = document.createElement("div");
                return this._setStyles(t, ["position: relative", "top: 50%", "max-width: 350px", 'font-family: "HelveticaNeue", "HelveticaNeue-Light", "Helvetica Neue Light", helvetica, arial, sans-serif', "font-size: 14px", "line-height: 20px", "margin: -70px auto 0"]), t.appendChild(this._createLogo()), t.appendChild(this._createExplanation()), t.appendChild(this._createFocusLink()), t
            }, n.prototype._createExplanation = function () {
                var t = document.createElement("div");
                return this._setStyles(t, ["color: #FFF", "margin-bottom: 20px"]), t.innerHTML = "Don't see the secure PayPal browser? We'll help you re-launch the window to complete your purchase.", t
            }, n.prototype._createLogo = function () {
                var t = document.createElement("div");
                return this._setStyles(t, ["background: url(" + this.spriteSrc + ") no-repeat 0 0", "width: 94px", "height: 25px", "margin: 0 auto 26px auto"]), t
            }, n.prototype._createFocusLink = function () {
                return this.focusLink = document.createElement("a"), this._setStyles(this.focusLink, ["color: #009be1", "cursor: pointer"]), this.focusLink.innerHTML = "Continue", this.focusLink
            }, n.prototype._setStyles = function (t, e) {
                var n = e.join(";");
                t.style.cssText = n
            }, n.prototype._setupEvents = function () {
                r.addEventListener(this.closeIcon, "click", r.bind(this._handleClose, this)), r.addEventListener(this.focusLink, "click", r.bind(this._handleFocus, this))
            }, n.prototype._handleClose = function (t) {
                t.preventDefault(), this.remove(), this.popup.close()
            }, n.prototype._handleFocus = function (t) {
                t.preventDefault(), this.popup.focus()
            }, n.prototype._pollForPopup = function () {
                var t = setInterval(r.bind(function () {
                    this.popup && this.popup.closed && (clearInterval(t), this.remove())
                }, this), 100)
            }, e.exports = n
        }, {"../shared/constants": 201, "braintree-utilities": 196}],
        201: [function (t, e, n) {
            var r = "1.5.3";
            n.VERSION = r, n.POPUP_NAME = "braintree_paypal_popup", n.ARIES_POPUP_NAME = "PPFrameRedirect", n.FRAME_NAME = "braintree-paypal-frame", n.ARIES_FRAME_NAME = "PPFrameRedirect", n.POPUP_PATH = "/pwpp/" + r + "/html/braintree-frame.html", n.POPUP_OPTIONS = "resizable,scrollbars", n.POPUP_HEIGHT = 470, n.POPUP_WIDTH = 410, n.ARIES_POPUP_HEIGHT = 535, n.ARIES_POPUP_WIDTH = 450, n.BRIDGE_FRAME_NAME = "bt-proxy-frame", n.ARIES_SUPPORTED_CURRENCIES = ["USD", "GBP", "EUR", "AUD", "CAD", "DKK", "NOK", "PLN", "SEK", "CHF", "TRY"], n.ARIES_SUPPORTED_COUNTRIES = ["US", "GB", "AU", "CA", "ES", "FR", "DE", "IT", "NL", "NO", "PL", "CH", "TR", "DK", "BE", "AT"], n.NONCE_TYPE = "PayPalAccount", n.ILLEGAL_XHR_ERROR = "Illegal XHR request attempted"
        }, {}],
        202: [function (t, e) {
            "use strict";
            e.exports = {
                us: "en_us",
                gb: "en_uk",
                uk: "en_uk",
                de: "de_de",
                fr: "fr_fr",
                it: "it_it",
                es: "es_es",
                ca: "en_ca",
                au: "en_au",
                at: "de_de",
                be: "en_us",
                ch: "de_de",
                dk: "da_dk",
                nl: "nl_nl",
                no: "no_no",
                pl: "pl_pl",
                se: "sv_se",
                tr: "tr_tr",
                bg: "en_us",
                cy: "en_us",
                hr: "en_us",
                is: "en_us",
                kh: "en_us",
                mt: "en_us",
                my: "en_us",
                ru: "ru_ru"
            }
        }, {}],
        203: [function (t, e) {
            "use strict";
            function n(t) {
                return -1 !== t.indexOf("_") && 5 === t.length
            }

            function r(t) {
                var e;
                for (var n in o)o.hasOwnProperty(n) && (n === t ? e = o[n] : o[n] === t && (e = o[n]));
                return e
            }

            function i(t) {
                var e;
                if (t = t ? t.toLowerCase() : "us", t = t.replace(/-/g, "_"), e = n(t) ? t : r(t)) {
                    var i = e.split("_");
                    return [i[0], i[1].toUpperCase()].join("_")
                }
                return "en_US"
            }

            var o = t("../shared/data/country-code-lookup");
            e.exports = i
        }, {"../shared/data/country-code-lookup": 202}],
        204: [function (t, e) {
            function n() {
                return p.matchUserAgent("Android") && !r()
            }

            function r() {
                return p.matchUserAgent("Chrome") || p.matchUserAgent("CriOS")
            }

            function i() {
                return p.matchUserAgent("Firefox")
            }

            function o() {
                return p.matchUserAgent("Trident") || p.matchUserAgent("MSIE")
            }

            function s() {
                return p.matchUserAgent("Opera") || p.matchUserAgent("OPR")
            }

            function a() {
                return s() && "[object OperaMini]" === d.call(window.operamini)
            }

            function u() {
                return p.matchUserAgent("Safari") && !r() && !n()
            }

            function c() {
                return h.isIos() && !r() && !u()
            }

            function l() {
                var t = /Version\/[\w\.]+ Chrome\/[\w\.]+ Mobile/;
                return h.isAndroid() && p.matchUserAgent(t)
            }

            var p = t("./useragent"), h = t("./platform"), d = Object.prototype.toString;
            e.exports = {
                isAndroid: n,
                isChrome: r,
                isFirefox: i,
                isIE: o,
                isOpera: s,
                isOperaMini: a,
                isSafari: u,
                isIosWebView: c,
                isAndroidWebView: l
            }
        }, {"./platform": 206, "./useragent": 207}],
        205: [function (t, e) {
            function n() {
                return !r() && (s.isAndroid() || s.isIpod() || s.isIphone() || o.matchUserAgent("IEMobile"))
            }

            function r() {
                return s.isIpad() || s.isAndroid() && !o.matchUserAgent("Mobile")
            }

            function i() {
                return !n() && !r()
            }

            var o = t("./useragent"), s = t("./platform");
            e.exports = {isMobile: n, isTablet: r, isDesktop: i}
        }, {"./platform": 206, "./useragent": 207}],
        206: [function (t, e) {
            function n() {
                return a.matchUserAgent("Android")
            }

            function r() {
                return a.matchUserAgent("iPad")
            }

            function i() {
                return a.matchUserAgent("iPod")
            }

            function o() {
                return a.matchUserAgent("iPhone") && !i()
            }

            function s() {
                return r() || i() || o()
            }

            var a = t("./useragent");
            e.exports = {isAndroid: n, isIpad: r, isIpod: i, isIphone: o, isIos: s}
        }, {"./useragent": 207}],
        207: [function (t, e, n) {
            function r() {
                return o
            }

            function i(t) {
                var e = n.getNativeUserAgent(), r = e.match(t);
                return r ? !0 : !1
            }

            var o = window.navigator.userAgent;
            n.getNativeUserAgent = r, n.matchUserAgent = i
        }, {}],
        208: [function (t, e) {
            function n() {
                return r() && window.outerWidth < 600
            }

            function r() {
                return f.test(d)
            }

            function i() {
                return !!window.postMessage
            }

            function o() {
                if (c.isOperaMini())return !1;
                if (l.isDesktop())return !0;
                if (l.isMobile() || l.isTablet()) {
                    if (c.isIE())return !1;
                    if (p.isAndroid())return c.isAndroidWebView() ? !1 : !0;
                    if (p.isIos())return c.isChrome() || c.isSafari() && h.matchUserAgent(/OS (?:8_1|8_0|8)(?!_\d)/i) || c.isIosWebView() ? !1 : !0
                }
                return !1
            }

            function s() {
                if (c.isIE() && h.matchUserAgent(/MSIE 8\.0/))return !1;
                try {
                    return window.self === window.top
                } catch (t) {
                    return !1
                }
            }

            function a() {
                return c.isIE()
            }

            function u() {
                var t = null, e = "";
                try {
                    new ActiveXObject("")
                } catch (n) {
                    e = n.name
                }
                try {
                    t = !!new ActiveXObject("htmlfile")
                } catch (n) {
                    t = !1
                }
                return t = "ReferenceError" !== e && t === !1 ? !1 : !0, !t
            }

            var c = t("../useragent/browser"), l = t("../useragent/device"), p = t("../useragent/platform"), h = t("../useragent/useragent"), d = window.navigator.userAgent, f = /[Mm]obi|tablet|iOS|Android|IEMobile|Windows\sPhone/;
            e.exports = {
                isMobile: n,
                isMobileDevice: r,
                detectedPostMessage: i,
                isPopupSupported: o,
                isOverlaySupported: s,
                isProxyFrameRequired: a,
                isMetroBrowser: u
            }
        }, {
            "../useragent/browser": 204,
            "../useragent/device": 205,
            "../useragent/platform": 206,
            "../useragent/useragent": 207
        }],
        209: [function (t, e) {
            function n(t, e) {
                var n = "innerText";
                document && document.body && "textContent"in document.body && (n = "textContent"), t[n] = e
            }

            e.exports = {setTextContent: n}
        }, {}],
        210: [function (t, e) {
            function n() {
                for (var t = "", e = 0; 32 > e; e++) {
                    var n = Math.floor(16 * Math.random());
                    t += n.toString(16)
                }
                return t
            }

            function r(t) {
                return /^(true|1)$/i.test(t)
            }

            function i(t) {
                return t.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\"/g, "&quot;").replace(/\'/g, "&apos;")
            }

            function o(t) {
                var e = t.indexOf("?"), n = {};
                if (e >= 0 && (t = t.substr(e + 1)), 0 !== t.length) {
                    for (var r = t.split("&"), i = 0, o = r.length; o > i; i++) {
                        var s = r[i], a = s.indexOf("="), u = s.substr(0, a), c = s.substr(a + 1), l = decodeURIComponent(c);
                        l = l.replace(/</g, "&lt;").replace(/>/g, "&gt;"), "false" === l && (l = !1), (void 0 === l || "true" === l) && (l = !0), n[u] = l
                    }
                    return n
                }
            }

            function s(t) {
                return t && "[object Function]" === Object.prototype.toString.call(t)
            }

            function a(t) {
                t.preventDefault ? t.preventDefault() : t.returnValue = !1
            }

            var u = "function" == typeof String.prototype.trim ? function (t) {
                return t.trim()
            } : function (t) {
                return t.replace(/^\s+|\s+$/, "")
            }, c = "function" == typeof window.btoa ? function (t) {
                return window.btoa(t)
            } : function (t) {
                for (var e, n, r, i, o, s, a, u = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", c = "", l = 0; l < t.length;)e = t.charCodeAt(l++), n = t.charCodeAt(l++), r = t.charCodeAt(l++), i = e >> 2, o = (3 & e) << 4 | n >> 4, s = (15 & n) << 2 | r >> 6, a = 63 & r, isNaN(n) ? s = a = 64 : isNaN(r) && (a = 64), c = c + u.charAt(i) + u.charAt(o) + u.charAt(s) + u.charAt(a);
                return c
            };
            e.exports = {
                trim: u,
                btoa: c,
                generateUid: n,
                castToBoolean: r,
                htmlEscape: i,
                parseUrlParams: o,
                isFunction: s,
                preventDefault: a
            }
        }, {}],
        211: [function (t, e) {
            "use strict";
            function n(t) {
                this.host = t || window, this.handlers = [], r.addEventListener(this.host, "message", r.bind(this.receive, this))
            }

            var r = t("braintree-utilities");
            n.prototype.receive = function (t) {
                var e, r, i, o;
                try {
                    i = JSON.parse(t.data)
                } catch (s) {
                    return
                }
                for (o = i.type, r = new n.Message(this, t.source, i.data), e = 0; e < this.handlers.length; e++)this.handlers[e].type === o && this.handlers[e].handler(r)
            }, n.prototype.send = function (t, e, n) {
                t.postMessage(JSON.stringify({type: e, data: n}), "*")
            }, n.prototype.register = function (t, e) {
                this.handlers.push({type: t, handler: e})
            }, n.prototype.unregister = function (t, e) {
                for (var n = this.handlers.length - 1; n >= 0; n--)if (this.handlers[n].type === t && this.handlers[n].handler === e)return this.handlers.splice(n, 1)
            }, n.Message = function (t, e, n) {
                this.bus = t, this.source = e, this.content = n
            }, n.Message.prototype.reply = function (t, e) {
                this.bus.send(this.source, t, e)
            }, e.exports = n
        }, {"braintree-utilities": 221}],
        212: [function (t, e) {
            "use strict";
            function n(t, e) {
                this.bus = t, this.target = e, this.handlers = [], this.bus.register("publish", r.bind(this._handleMessage, this))
            }

            var r = t("braintree-utilities");
            n.prototype._handleMessage = function (t) {
                var e, n = t.content, r = this.handlers[n.channel];
                if ("undefined" != typeof r)for (e = 0; e < r.length; e++)r[e](n.data)
            }, n.prototype.publish = function (t, e) {
                this.bus.send(this.target, "publish", {channel: t, data: e})
            }, n.prototype.subscribe = function (t, e) {
                this.handlers[t] = this.handlers[t] || [], this.handlers[t].push(e)
            }, n.prototype.unsubscribe = function (t, e) {
                var n, r = this.handlers[t];
                if ("undefined" != typeof r)for (n = 0; n < r.length; n++)r[n] === e && r.splice(n, 1)
            }, e.exports = n
        }, {"braintree-utilities": 221}],
        213: [function (t, e) {
            "use strict";
            function n(t) {
                this.bus = t, this.frames = [], this.handlers = []
            }

            n.prototype.subscribe = function (t, e) {
                this.handlers[t] = this.handlers[t] || [], this.handlers[t].push(e)
            }, n.prototype.registerFrame = function (t) {
                this.frames.push(t)
            }, n.prototype.unregisterFrame = function (t) {
                for (var e = 0; e < this.frames.length; e++)this.frames[e] === t && this.frames.splice(e, 1)
            }, n.prototype.publish = function (t, e) {
                var n, r = this.handlers[t];
                if ("undefined" != typeof r)for (n = 0; n < r.length; n++)r[n](e);
                for (n = 0; n < this.frames.length; n++)this.bus.send(this.frames[n], "publish", {channel: t, data: e})
            }, n.prototype.unsubscribe = function (t, e) {
                var n, r = this.handlers[t];
                if ("undefined" != typeof r)for (n = 0; n < r.length; n++)r[n] === e && r.splice(n, 1)
            }, e.exports = n
        }, {}],
        214: [function (t, e) {
            "use strict";
            function n(t, e) {
                this.bus = t, this.target = e || window.parent, this.counter = 0, this.callbacks = {}, this.bus.register("rpc_response", r.bind(this._handleResponse, this))
            }

            var r = t("braintree-utilities");
            n.prototype._handleResponse = function (t) {
                var e = t.content, n = this.callbacks[e.id];
                "function" == typeof n && (n.apply(null, e.response), delete this.callbacks[e.id])
            }, n.prototype.invoke = function (t, e, n) {
                var r = this.counter++;
                this.callbacks[r] = n, this.bus.send(this.target, "rpc_request", {id: r, method: t, args: e})
            }, e.exports = n
        }, {"braintree-utilities": 221}],
        215: [function (t, e) {
            "use strict";
            function n(t) {
                this.bus = t, this.methods = {}, this.bus.register("rpc_request", r.bind(this._handleRequest, this))
            }

            var r = t("braintree-utilities");
            n.prototype._handleRequest = function (t) {
                var e, n = t.content, r = n.args || [], i = this.methods[n.method];
                "function" == typeof i && (e = function () {
                    t.reply("rpc_response", {id: n.id, response: Array.prototype.slice.call(arguments)})
                }, r.push(e), i.apply(null, r))
            }, n.prototype.define = function (t, e) {
                this.methods[t] = e
            }, e.exports = n
        }, {"braintree-utilities": 221}],
        216: [function (t, e) {
            var n = t("./lib/message-bus"), r = t("./lib/pubsub-client"), i = t("./lib/pubsub-server"), o = t("./lib/rpc-client"), s = t("./lib/rpc-server");
            e.exports = {MessageBus: n, PubsubClient: r, PubsubServer: i, RPCClient: o, RPCServer: s}
        }, {
            "./lib/message-bus": 211,
            "./lib/pubsub-client": 212,
            "./lib/pubsub-server": 213,
            "./lib/rpc-client": 214,
            "./lib/rpc-server": 215
        }],
        217: [function (t, e) {
            "use strict";
            function n(t, e) {
                if (e = e || "[" + t + "] is not a valid DOM Element", t && t.nodeType && 1 === t.nodeType)return t;
                if (t && window.jQuery && t instanceof jQuery && 0 !== t.length)return t[0];
                if ("string" == typeof t && document.getElementById(t))return document.getElementById(t);
                throw new Error(e)
            }

            e.exports = {normalizeElement: n}
        }, {}],
        218: [function (t, e) {
            "use strict";
            function n(t, e, n) {
                t.addEventListener ? t.addEventListener(e, n, !1) : t.attachEvent && t.attachEvent("on" + e, n)
            }

            function r(t, e, n) {
                t.removeEventListener ? t.removeEventListener(e, n, !1) : t.detachEvent && t.detachEvent("on" + e, n)
            }

            e.exports = {removeEventListener: r, addEventListener: n}
        }, {}],
        219: [function (t, e) {
            "use strict";
            function n(t) {
                return "[object Function]" === Object.prototype.toString.call(t)
            }

            function r(t, e) {
                return function () {
                    t.apply(e, arguments)
                }
            }

            e.exports = {bind: r, isFunction: n}
        }, {}],
        220: [function (t, e, n) {
            arguments[4][25][0].apply(n, arguments)
        }, {dup: 25}],
        221: [function (t, e, n) {
            arguments[4][26][0].apply(n, arguments)
        }, {"./lib/dom": 217, "./lib/events": 218, "./lib/fn": 219, "./lib/url": 220, dup: 26}],
        222: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        223: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        224: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        225: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        226: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        227: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 222, dup: 40}],
        228: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 222,
            "./lib/dom": 223,
            "./lib/events": 224,
            "./lib/fn": 225,
            "./lib/string": 226,
            "./lib/url": 227,
            dup: 41
        }],
        229: [function (t, e) {
            (function (t) {
                "use strict";
                function n(t) {
                    if (("string" == typeof t || t instanceof String) && (t = document.getElementById(t)), !(t instanceof HTMLFormElement))throw new TypeError("FormNapper requires an HTMLFormElement element or the id string of one.");
                    this.htmlForm = t
                }

                n.prototype.hijack = function (e) {
                    function n(t) {
                        t.preventDefault ? t.preventDefault() : t.returnValue = !1, e(t)
                    }

                    null != t.addEventListener ? this.htmlForm.addEventListener("submit", n, !1) : null != t.attachEvent ? this.htmlForm.attachEvent("onsubmit", n) : this.htmlForm.onsubmit = n
                }, n.prototype.inject = function (t, e) {
                    var n = this.htmlForm.querySelector('input[name="' + t + '"]');
                    null == n && (n = document.createElement("input"), n.type = "hidden", n.name = t, this.htmlForm.appendChild(n)), n.value = e
                }, n.prototype.submit = function () {
                    HTMLFormElement.prototype.submit.call(this.htmlForm)
                }, e.exports = n
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {}],
        230: [function (t, e, n) {
            arguments[4][74][0].apply(n, arguments)
        }, {"./lib/default-attributes": 231, dup: 74, "lodash.assign": 232, "lodash.isstring": 243}],
        231: [function (t, e, n) {
            arguments[4][75][0].apply(n, arguments)
        }, {dup: 75}],
        232: [function (t, e, n) {
            arguments[4][76][0].apply(n, arguments)
        }, {dup: 76, "lodash._baseassign": 233, "lodash._createassigner": 235, "lodash.keys": 239}],
        233: [function (t, e, n) {
            arguments[4][77][0].apply(n, arguments)
        }, {dup: 77, "lodash._basecopy": 234, "lodash.keys": 239}],
        234: [function (t, e, n) {
            arguments[4][78][0].apply(n, arguments)
        }, {dup: 78}],
        235: [function (t, e, n) {
            arguments[4][79][0].apply(n, arguments)
        }, {dup: 79, "lodash._bindcallback": 236, "lodash._isiterateecall": 237, "lodash.restparam": 238}],
        236: [function (t, e, n) {
            arguments[4][80][0].apply(n, arguments)
        }, {dup: 80}],
        237: [function (t, e, n) {
            arguments[4][81][0].apply(n, arguments)
        }, {dup: 81}],
        238: [function (t, e, n) {
            arguments[4][82][0].apply(n, arguments)
        }, {dup: 82}],
        239: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 240, "lodash.isarguments": 241, "lodash.isarray": 242}],
        240: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        241: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        242: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        243: [function (t, e, n) {
            arguments[4][49][0].apply(n, arguments)
        }, {dup: 49}],
        244: [function (t, e) {
            "use strict";
            function n(t) {
                this.apiClient = t
            }

            var r = ["getCreditCards", "unlockCreditCard", "sendAnalyticsEvents"];
            n.prototype.attach = function (t) {
                function e(e) {
                    t.define(e, function () {
                        n.apiClient[e].apply(n.apiClient, arguments)
                    })
                }

                var n = this, i = 0, o = r.length;
                for (i; o > i; i++)e(r[i])
            }, e.exports = n
        }, {}],
        245: [function (t, e) {
            "use strict";
            function n(t, e) {
                var n = window.getComputedStyle ? getComputedStyle(t) : t.currentStyle;
                return n[e]
            }

            function r() {
                return {
                    html: {height: s.style.height || "", overflow: n(s, "overflow"), position: n(s, "position")},
                    body: {height: a.style.height || "", overflow: n(a, "overflow")}
                }
            }

            function i() {
                var t = /Android|iPhone|iPod|iPad/i.test(window.navigator.userAgent);
                return t
            }

            function o(t) {
                var e, n, r;
                this.merchantConfiguration = t.merchantConfiguration, this.encodedClientToken = t.gatewayConfiguration, this.analyticsConfiguration = t.analyticsConfiguration, this.paypalOptions = t.merchantConfiguration.paypal, this.container = null, this.merchantFormManager = null, this.root = t.root, this.configurationRequests = [], this.braintreeApiClient = u.configure({
                    clientToken: t.gatewayConfiguration,
                    analyticsConfiguration: this.analyticsConfiguration,
                    integration: "dropin",
                    enableCORS: this.merchantConfiguration.enableCORS
                }), this.paymentMethodNonceReceivedCallback = t.merchantConfiguration.paymentMethodNonceReceived, this.clientToken = u.parseClientToken(t.gatewayConfiguration), this.bus = new l.MessageBus(this.root), this.rpcServer = new l.RPCServer(this.bus), this.apiProxyServer = new h(this.braintreeApiClient), this.apiProxyServer.attach(this.rpcServer), e = t.inlineFramePath || this.clientToken.assetsUrl + "/dropin/" + b + "/inline-frame.html", n = t.modalFramePath || this.clientToken.assetsUrl + "/dropin/" + b + "/modal-frame.html", s = document.documentElement, a = document.body, this.frames = {
                    inline: this._createFrame(e, g.INLINE_FRAME_NAME),
                    modal: this._createFrame(n, g.MODAL_FRAME_NAME)
                }, this.container = p.normalizeElement(t.merchantConfiguration.container, "Unable to find valid container."), r = p.normalizeElement(t.merchantConfiguration.form || this._findClosest(this.container, "form")), this.merchantFormManager = new d({
                    form: r,
                    frames: this.frames,
                    onSubmit: this.paymentMethodNonceReceivedCallback,
                    apiClient: this.braintreeApiClient
                }).initialize(), t.gatewayConfiguration.paypalEnabled && t.gatewayConfiguration.paypal && (p.isBrowserHttps() || t.gatewayConfiguration.paypal.allowHttp) && this._configurePayPal(), this.braintreeApiClient.sendAnalyticsEvents("dropin.web.initialized")
            }

            var s, a, u = t("braintree-api"), c = t("braintree-bus"), l = t("braintree-rpc"), p = t("braintree-utilities"), h = t("./api-proxy-server"), d = t("./merchant-form-manager"), f = t("./frame-container"), m = t("../shared/paypal-service"), g = t("../shared/constants"), y = t("braintree-paypal/src/shared/util/browser"), b = "1.8.3";
            o.prototype.initialize = function () {
                var t, e = this;
                this._initializeModal(), c.emit(c.events.ASYNC_DEPENDENCY_INITIALIZING), this.container.appendChild(this.frames.inline.element), a.appendChild(this.frames.modal.element), this.rpcServer.define("receiveSharedCustomerIdentifier", function (n) {
                    for (e.braintreeApiClient.attrs.sharedCustomerIdentifier = n, e.braintreeApiClient.attrs.sharedCustomerIdentifierType = "browser_session_cookie_store", t = 0; t < e.configurationRequests.length; t++)e.configurationRequests[t](e.encodedClientToken);
                    e.configurationRequests = []
                }), c.on(c.events.PAYMENT_METHOD_GENERATED, p.bind(this._handleAltPayData, this)), this.rpcServer.define("getConfiguration", function (t) {
                    t({
                        enableCORS: e.merchantConfiguration.enableCORS,
                        clientToken: e.encodedClientToken,
                        analyticsConfiguration: e.analyticsConfiguration,
                        merchantHttps: p.isBrowserHttps()
                    })
                }), this.rpcServer.define("getPayPalOptions", function (t) {
                    t(e.paypalOptions)
                }), this.rpcServer.define("selectPaymentMethod", function (t) {
                    e.frames.modal.rpcClient.invoke("selectPaymentMethod", [t]), e._showModal()
                }), this.rpcServer.define("sendAddedPaymentMethod", function (t) {
                    e.merchantFormManager.setNoncePayload(t), e.frames.inline.rpcClient.invoke("receiveNewPaymentMethod", [t])
                }), this.rpcServer.define("sendUsedPaymentMethod", function (t) {
                    e.frames.inline.rpcClient.invoke("selectPaymentMethod", [t])
                }), this.rpcServer.define("sendUnlockedNonce", function (t) {
                    e.merchantFormManager.setNoncePayload(t)
                }), this.rpcServer.define("clearNonce", function () {
                    e.merchantFormManager.clearNoncePayload()
                }), this.rpcServer.define("closeDropInModal", function () {
                    e._hideModal()
                }), this.rpcServer.define("setInlineFrameHeight", function (t) {
                    e.frames.inline.element.style.height = t + "px"
                }), this.bus.register("ready", function (t) {
                    t.source === e.frames.inline.element.contentWindow ? e.frames.inline.rpcClient = new l.RPCClient(e.bus, t.source) : t.source === e.frames.modal.element.contentWindow && (e.frames.modal.rpcClient = new l.RPCClient(e.bus, t.source))
                })
            }, o.prototype._createFrame = function (t, e) {
                return new f(t, e)
            }, o.prototype._initializeModal = function () {
                this.frames.modal.element.style.display = "none", this.frames.modal.element.style.position = i() ? "absolute" : "fixed", this.frames.modal.element.style.top = "0", this.frames.modal.element.style.left = "0", this.frames.modal.element.style.height = "100%", this.frames.modal.element.style.width = "100%"
            }, o.prototype._lockMerchantWindowSize = function () {
                setTimeout(function () {
                    s.style.overflow = "hidden", a.style.overflow = "hidden", a.style.height = "100%", i() && (s.style.position = "relative", s.style.height = window.innerHeight + "px")
                }, 160)
            }, o.prototype._unlockMerchantWindowSize = function () {
                var t = this.merchantPageDefaultStyles;
                a.style.height = t.body.height, a.style.overflow = t.body.overflow, s.style.overflow = t.html.overflow, i() && (s.style.height = t.html.height, s.style.position = t.html.position)
            }, o.prototype._showModal = function () {
                var t = this, e = this.frames.modal.element;
                this.merchantPageDefaultStyles = r(), e.style.display = "block", this.frames.modal.rpcClient.invoke("open", [], function () {
                    setTimeout(function () {
                        t._lockMerchantWindowSize(), e.contentWindow.focus()
                    }, 200)
                })
            }, o.prototype._hideModal = function () {
                this._unlockMerchantWindowSize(), this.frames.modal.element.style.display = "none"
            }, o.prototype._configurePayPal = function () {
                y.isPopupSupported() || (this.ppClient = new m({
                    clientToken: this.clientToken,
                    paypal: this.paypalOptions
                }), this.rpcServer.define("openPayPalModal", p.bind(this.ppClient._openModal, this.ppClient))), c.on("receivePayPalData", p.bind(this._handleAltPayData, this))
            }, o.prototype._handleAltPayData = function (t) {
                this.merchantFormManager.setNoncePayload(t), this.frames.inline.rpcClient.invoke("receiveNewPaymentMethod", [t]), this.frames.modal.rpcClient.invoke("modalViewClose")
            }, o.prototype._findClosest = function (t, e) {
                e = e.toUpperCase();
                do if (t.nodeName === e)return t; while (t = t.parentNode);
                throw"Unable to find a valid " + e
            }, e.exports = o
        }, {
            "../shared/constants": 249,
            "../shared/paypal-service": 250,
            "./api-proxy-server": 244,
            "./frame-container": 247,
            "./merchant-form-manager": 248,
            "braintree-api": 107,
            "braintree-bus": 136,
            "braintree-paypal/src/shared/util/browser": 208,
            "braintree-rpc": 216,
            "braintree-utilities": 228
        }],
        246: [function (t, e) {
            "use strict";
            function n(t) {
                var e = new r(t);
                return e.initialize(), e
            }

            var r = t("./client"), i = "1.8.3";
            e.exports = {create: n, VERSION: i}
        }, {"./client": 245}],
        247: [function (t, e) {
            "use strict";
            function n() {
                var t, e = document.createElement("fakeelement");
                for (t in u)if ("undefined" != typeof e.style[t])return u[t];
                return null
            }

            function r(t) {
                function e(n) {
                    n.target === t && "height" === n.propertyName && (o.emit(o.events.ASYNC_DEPENDENCY_READY), t.removeEventListener(r, e))
                }

                var r = n();
                r ? t.addEventListener(r, e) : setTimeout(function () {
                    o.emit(o.events.ASYNC_DEPENDENCY_READY)
                }, 500)
            }

            function i(t, e) {
                var n = "height 210ms cubic-bezier(0.390, 0.575, 0.565, 1.000)";
                this.element = a({
                    name: e,
                    width: "100%",
                    height: "68",
                    style: {
                        transition: n,
                        WebkitTransition: n,
                        MozTransition: n,
                        msTransition: n,
                        OTransition: n,
                        border: "0",
                        zIndex: "9999"
                    }
                }), this.element.src = t, e === s.INLINE_FRAME_NAME && r(this.element)
            }

            var o = t("braintree-bus"), s = t("../shared/constants"), a = t("iframer"), u = {
                transition: "transitionend",
                "-o-transition": "otransitionEnd",
                "-moz-transition": "transitionend",
                "-webkit-transition": "webkitTransitionEnd"
            };
            e.exports = i
        }, {"../shared/constants": 249, "braintree-bus": 136, iframer: 230}],
        248: [function (t, e) {
            "use strict";
            function n(t) {
                this.formNapper = new i(t.form), this.frames = t.frames, this.onSubmit = t.onSubmit, this.apiClient = t.apiClient
            }

            var r = t("braintree-utilities"), i = t("form-napper");
            n.prototype.initialize = function () {
                return this._isSubmitBased() && this._setElements(), this._setEvents(), this
            }, n.prototype.setNoncePayload = function (t) {
                this.noncePayload = t
            }, n.prototype.clearNoncePayload = function () {
                this.noncePayload = null
            }, n.prototype._isSubmitBased = function () {
                return !this.onSubmit
            }, n.prototype._isCallbackBased = function () {
                return !!this.onSubmit
            }, n.prototype._setElements = function () {
                this.formNapper.inject("payment_method_nonce", "")
            }, n.prototype._setEvents = function () {
                this.formNapper.hijack(r.bind(this._handleFormSubmit, this))
            }, n.prototype._handleFormSubmit = function (t) {
                this.noncePayload && this.noncePayload.nonce ? this._handleNonceReply(t) : this.frames.inline.rpcClient.invoke("requestNonce", [], r.bind(function (e) {
                    this.setNoncePayload(e), this._handleNonceReply(t)
                }, this))
            }, n.prototype._handleNonceReply = function (t) {
                this._isCallbackBased() ? this.apiClient.sendAnalyticsEvents("dropin.web.end.callback", r.bind(function () {
                    var e = this.noncePayload;
                    e.originalEvent = t, this.onSubmit(e), setTimeout(r.bind(function () {
                        this.frames.inline.rpcClient.invoke("clearLoadingState"), this.frames.inline.rpcClient.invoke("receiveNewPaymentMethod", [e])
                    }, this), 200)
                }, this)) : this._triggerFormSubmission()
            }, n.prototype._triggerFormSubmission = function () {
                this.formNapper.inject("payment_method_nonce", this.noncePayload.nonce), this.apiClient.sendAnalyticsEvents("dropin.web.end.auto-submit", r.bind(function () {
                    this.formNapper.submit()
                }, this))
            }, e.exports = n
        }, {"braintree-utilities": 228, "form-napper": 229}],
        249: [function (t, e) {
            e.exports = {
                PAYPAL_INTEGRATION_NAME: "PayPal",
                INLINE_FRAME_NAME: "braintree-dropin-frame",
                MODAL_FRAME_NAME: "braintree-dropin-modal-frame",
                PAYMENT_METHOD_TYPES: ["CoinbaseAccount", "PayPalAccount", "CreditCard"],
                cssClassMap: {
                    "American Express": "american-express",
                    "Diners Club": "diners-club",
                    DinersClub: "diners-club",
                    Discover: "discover",
                    JCB: "jcb",
                    Maestro: "maestro",
                    MasterCard: "master-card",
                    Solo: "solo",
                    Switch: "switch",
                    UKMaestro: "maestro",
                    UnionPay: "unionpay",
                    Visa: "visa"
                }
            }
        }, {}],
        250: [function (t, e) {
            "use strict";
            function n(t) {
                var e = t.clientToken, n = t.paypal || {}, i = new r(e, {
                    container: document.createElement("div"),
                    displayName: n.displayName,
                    locale: n.locale,
                    singleUse: n.singleUse,
                    amount: n.amount,
                    currency: n.currency,
                    onSuccess: n.onSuccess,
                    enableShippingAddress: n.enableShippingAddress,
                    shippingAddressOverride: n.shippingAddressOverride,
                    enableBillingAddress: n.enableBillingAddress
                });
                return i.initialize(), i
            }

            var r = t("braintree-paypal/src/external/client");
            e.exports = n
        }, {"braintree-paypal/src/external/client": 197}],
        251: [function (t, e) {
            (function (t) {
                "use strict";
                function n(t, e) {
                    e = e || {};
                    var i, s, a = t.children;
                    for (s = 0; s < a.length; s++)if (i = a[s], o(i)) {
                        var u = i.getAttribute("data-braintree-name");
                        "postal_code" === u ? e.billingAddress = {postalCode: i.value} : e[u] = i.value, r(i)
                    } else i.children && i.children.length > 0 && n(i, e);
                    return e
                }

                function r(t) {
                    try {
                        t.attributes.removeNamedItem("name")
                    } catch (e) {
                    }
                }

                function i(t) {
                    n(t)
                }

                function o(t) {
                    return t.nodeType === s && t.attributes["data-braintree-name"]
                }

                var s = t.Node ? t.Node.ELEMENT_NODE : 1;
                e.exports = {extractValues: n, scrubAllAttributes: i, scrubAttributes: r, isBraintreeNode: o}
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {}],
        252: [function (t, e) {
            "use strict";
            function n(t, e, n, r) {
                this.client = t, this.htmlForm = e, this.isCreditCardForm = r === !1 ? !1 : !0, this.paymentMethodNonceInput = n, this.model = new s, this.setEvents()
            }

            var r = t("braintree-utilities"), i = t("./fields"), o = t("braintree-bus"), s = t("./models/payment-method-model"), a = {
                message: "Unable to process payments at this time",
                type: "IMMEDIATE"
            };
            n.prototype.setEvents = function () {
                this.onSubmitHandler = r.bind(this.handleSubmit, this), this.onExternalNonceReceived = r.bind(this.onExternalNonceReceived, this), this.clearExternalNonce = r.bind(this.clearExternalNonce, this), r.addEventListener(this.htmlForm, "submit", this.onSubmitHandler), o.on(o.events.PAYMENT_METHOD_GENERATED, this.onExternalNonceReceived), o.on(o.events.PAYMENT_METHOD_CANCELLED, this.clearExternalNonce)
            }, n.prototype.handleSubmit = function (t) {
                var e;
                return t.preventDefault ? t.preventDefault() : t.returnValue = !1, this.isCreditCardForm ? (e = this.model.get("type"), e && "CreditCard" !== e ? (i.scrubAllAttributes(this.htmlForm), void this.onNonceReceived(null, this.model.attributes)) : void this.client.tokenizeCard(i.extractValues(this.htmlForm), r.bind(function (t, e, n) {
                    t ? this.onNonceReceived(a, null) : (this.model.set({
                        nonce: e,
                        type: n.type,
                        details: n.details
                    }), this.onNonceReceived(null, this.model.attributes))
                }, this))) : void this.onNonceReceived(null, this.model.attributes)
            }, n.prototype.writeNonceToDOM = function () {
                this.paymentMethodNonceInput.value = this.model.get("nonce")
            }, n.prototype.onExternalNonceReceived = function (t) {
                this.model.set(t), this.writeNonceToDOM()
            }, n.prototype.clearExternalNonce = function () {
                this.model.reset()
            }, n.prototype.onNonceReceived = function (t) {
                var e = this.htmlForm;
                return t ? void o.emit(o.events.ERROR, a) : (r.removeEventListener(e, "submit", this.onSubmitHandler), this.writeNonceToDOM(), void(e.submit && ("function" == typeof e.submit || e.submit.call) ? e.submit() : setTimeout(function () {
                    e.querySelector('[type="submit"]').click()
                }, 1)))
            }, e.exports = n
        }, {"./fields": 251, "./models/payment-method-model": 254, "braintree-bus": 257, "braintree-utilities": 264}],
        253: [function (t, e) {
            "use strict";
            e.exports = function (t) {
                var e;
                if ("object" == typeof t)return t;
                e = "payment_method_nonce", "string" == typeof t && (e = t);
                var n = document.createElement("input");
                return n.name = e, n.type = "hidden", n
            }
        }, {}],
        254: [function (t, e) {
            "use strict";
            function n() {
                this.reset()
            }

            n.prototype.get = function (t) {
                return this.attributes[t]
            }, n.prototype.set = function (t) {
                this.attributes = t || {}
            }, n.prototype.reset = function () {
                this.attributes = {}
            }, e.exports = n
        }, {}],
        255: [function (t, e) {
            "use strict";
            e.exports = function (t) {
                for (var e = t.getElementsByTagName("*"), n = {}, r = 0; r < e.length; r++) {
                    var i = e[r].getAttribute("data-braintree-name");
                    n[i] = !0
                }
                if (!n.number)throw new Error('Unable to find an input with data-braintree-name="number" in your form. Please add one.');
                if (n.expiration_date) {
                    if (n.expiration_month || n.expiration_year)throw new Error('You have inputs with data-braintree-name="expiration_date" AND data-braintree-name="expiration_(year|month)". Please use either "expiration_date" or "expiration_year" and "expiration_month".')
                } else {
                    if (!n.expiration_month && !n.expiration_year)throw new Error('Unable to find an input with data-braintree-name="expiration_date" in your form. Please add one.');
                    if (!n.expiration_month)throw new Error('Unable to find an input with data-braintree-name="expiration_month" in your form. Please add one.');
                    if (!n.expiration_year)throw new Error('Unable to find an input with data-braintree-name="expiration_year" in your form. Please add one.')
                }
            }
        }, {}],
        256: [function (t, e) {
            "use strict";
            function n(t, e) {
                var n, s, a = document.getElementById(e.id), u = e && e.hasOwnProperty("useCreditCard") ? e.useCreditCard : !0;
                if (!a)throw new Error('Unable to find form with id: "' + e.id + '"');
                return u && i(a), n = o(e.paymentMethodNonceInputField), a.appendChild(n), s = new r(t, a, n, u)
            }

            var r = t("./lib/form"), i = t("./lib/validate-annotations"), o = t("./lib/get-nonce-input");
            e.exports = {setup: n}
        }, {"./lib/form": 252, "./lib/get-nonce-input": 253, "./lib/validate-annotations": 255}],
        257: [function (t, e, n) {
            arguments[4][50][0].apply(n, arguments)
        }, {"./lib/events": 258, dup: 50, framebus: 259}],
        258: [function (t, e, n) {
            arguments[4][51][0].apply(n, arguments)
        }, {dup: 51}],
        259: [function (t, e, n) {
            arguments[4][52][0].apply(n, arguments)
        }, {dup: 52}],
        260: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        261: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        262: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        263: [function (t, e, n) {
            arguments[4][25][0].apply(n, arguments)
        }, {dup: 25}],
        264: [function (t, e, n) {
            arguments[4][26][0].apply(n, arguments)
        }, {"./lib/dom": 260, "./lib/events": 261, "./lib/fn": 262, "./lib/url": 263, dup: 26}],
        265: [function (t, e, n) {
            arguments[4][2][0].apply(n, arguments)
        }, {
            "./coinbase-account": 266,
            "./credit-card": 268,
            "./europe-bank-account": 269,
            "./normalize-api-fields": 271,
            "./parse-client-token": 272,
            "./paypal-account": 273,
            "./request/choose-driver": 276,
            "./sepa-mandate": 281,
            "./should-enable-cors": 282,
            "./util": 283,
            "braintree-3ds": 292,
            "braintree-utilities": 304,
            dup: 2
        }],
        266: [function (t, e, n) {
            arguments[4][3][0].apply(n, arguments)
        }, {dup: 3}],
        267: [function (t, e, n) {
            arguments[4][4][0].apply(n, arguments)
        }, {dup: 4}],
        268: [function (t, e, n) {
            arguments[4][5][0].apply(n, arguments)
        }, {dup: 5}],
        269: [function (t, e, n) {
            arguments[4][6][0].apply(n, arguments)
        }, {dup: 6}],
        270: [function (t, e, n) {
            arguments[4][7][0].apply(n, arguments)
        }, {
            "./parse-client-token": 272,
            "./request/choose-driver": 276,
            "./should-enable-cors": 282,
            "./util": 283,
            dup: 7
        }],
        271: [function (t, e, n) {
            arguments[4][8][0].apply(n, arguments)
        }, {dup: 8}],
        272: [function (t, e, n) {
            arguments[4][9][0].apply(n, arguments)
        }, {"./polyfill": 274, "braintree-utilities": 304, dup: 9}],
        273: [function (t, e, n) {
            arguments[4][10][0].apply(n, arguments)
        }, {dup: 10}],
        274: [function (t, e, n) {
            arguments[4][11][0].apply(n, arguments)
        }, {dup: 11}],
        275: [function (t, e, n) {
            arguments[4][12][0].apply(n, arguments)
        }, {"../constants": 267, "../util": 283, "./parse-body": 279, "./prep-body": 280, dup: 12}],
        276: [function (t, e, n) {
            arguments[4][13][0].apply(n, arguments)
        }, {"../util": 283, "./ajax-driver": 275, "./jsonp-driver": 277, dup: 13}],
        277: [function (t, e, n) {
            arguments[4][14][0].apply(n, arguments)
        }, {"../constants": 267, "./jsonp": 278, dup: 14}],
        278: [function (t, e, n) {
            arguments[4][15][0].apply(n, arguments)
        }, {"../util": 283, dup: 15}],
        279: [function (t, e, n) {
            arguments[4][16][0].apply(n, arguments)
        }, {dup: 16}],
        280: [function (t, e, n) {
            arguments[4][17][0].apply(n, arguments)
        }, {dup: 17, "lodash.isstring": 312}],
        281: [function (t, e, n) {
            arguments[4][18][0].apply(n, arguments)
        }, {dup: 18}],
        282: [function (t, e, n) {
            arguments[4][19][0].apply(n, arguments)
        }, {dup: 19}],
        283: [function (t, e, n) {
            arguments[4][20][0].apply(n, arguments)
        }, {dup: 20, "lodash.isempty": 305, "lodash.isobject": 311}],
        284: [function (t, e, n) {
            arguments[4][21][0].apply(n, arguments)
        }, {
            "./lib/client": 265,
            "./lib/get-configuration": 270,
            "./lib/parse-client-token": 272,
            "./lib/util": 283,
            dup: 21
        }],
        285: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        286: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        287: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        288: [function (t, e, n) {
            arguments[4][25][0].apply(n, arguments)
        }, {dup: 25}],
        289: [function (t, e, n) {
            arguments[4][26][0].apply(n, arguments)
        }, {"./lib/dom": 285, "./lib/events": 286, "./lib/fn": 287, "./lib/url": 288, dup: 26}],
        290: [function (t, e, n) {
            arguments[4][27][0].apply(n, arguments)
        }, {"../shared/receiver": 297, "braintree-utilities": 289, dup: 27}],
        291: [function (t, e, n) {
            arguments[4][28][0].apply(n, arguments)
        }, {"./authorization_service": 290, "./loader": 293, "braintree-utilities": 289, dup: 28}],
        292: [function (t, e, n) {
            arguments[4][29][0].apply(n, arguments)
        }, {"./client": 291, dup: 29}],
        293: [function (t, e, n) {
            arguments[4][30][0].apply(n, arguments)
        }, {"./loader_display": 294, "./loader_message": 295, "./loader_spinner": 296, dup: 30}],
        294: [function (t, e, n) {
            arguments[4][31][0].apply(n, arguments)
        }, {dup: 31}],
        295: [function (t, e, n) {
            arguments[4][32][0].apply(n, arguments)
        }, {dup: 32}],
        296: [function (t, e, n) {
            arguments[4][33][0].apply(n, arguments)
        }, {dup: 33}],
        297: [function (t, e, n) {
            arguments[4][34][0].apply(n, arguments)
        }, {"braintree-utilities": 289, dup: 34}],
        298: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        299: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        300: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        301: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        302: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        303: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 298, dup: 40}],
        304: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 298,
            "./lib/dom": 299,
            "./lib/events": 300,
            "./lib/fn": 301,
            "./lib/string": 302,
            "./lib/url": 303,
            dup: 41
        }],
        305: [function (t, e, n) {
            arguments[4][42][0].apply(n, arguments)
        }, {
            dup: 42,
            "lodash.isarguments": 306,
            "lodash.isarray": 307,
            "lodash.isfunction": 308,
            "lodash.isstring": 312,
            "lodash.keys": 309
        }],
        306: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        307: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        308: [function (t, e, n) {
            arguments[4][45][0].apply(n, arguments)
        }, {dup: 45}],
        309: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 310, "lodash.isarguments": 306, "lodash.isarray": 307}],
        310: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        311: [function (t, e, n) {
            arguments[4][48][0].apply(n, arguments)
        }, {dup: 48}],
        312: [function (t, e, n) {
            arguments[4][49][0].apply(n, arguments)
        }, {dup: 49}],
        313: [function (t, e, n) {
            arguments[4][50][0].apply(n, arguments)
        }, {"./lib/events": 314, dup: 50, framebus: 315}],
        314: [function (t, e, n) {
            arguments[4][51][0].apply(n, arguments)
        }, {dup: 51}],
        315: [function (t, e, n) {
            arguments[4][52][0].apply(n, arguments)
        }, {dup: 52}],
        316: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        317: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        318: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        319: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        320: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        321: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 316, dup: 40}],
        322: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 316,
            "./lib/dom": 317,
            "./lib/events": 318,
            "./lib/fn": 319,
            "./lib/string": 320,
            "./lib/url": 321,
            dup: 41
        }],
        323: [function (t, e, n) {
            arguments[4][197][0].apply(n, arguments)
        }, {
            "../shared/constants": 328,
            "../shared/get-locale": 330,
            "../shared/util/browser": 335,
            "../shared/util/dom": 336,
            "../shared/util/util": 337,
            "./logged-in-view": 325,
            "./logged-out-view": 326,
            "./overlay-view": 327,
            "braintree-api": 284,
            "braintree-bus": 313,
            "braintree-utilities": 322,
            dup: 197
        }],
        324: [function (t, e) {
            function n(t, e) {
                if (!i.detectedPostMessage())return void("function" == typeof e.onUnsupported && e.onUnsupported(new Error("unsupported browser detected")));
                var n = new r(t, e);
                return n.initialize(), n
            }

            var r = t("./client"), i = t("../shared/util/browser"), o = "1.5.3";
            e.exports = {create: n, _browser: i, VERSION: o}
        }, {"../shared/util/browser": 335, "./client": 323}],
        325: [function (t, e, n) {
            arguments[4][198][0].apply(n, arguments)
        }, {"../shared/constants": 328, dup: 198}],
        326: [function (t, e, n) {
            arguments[4][199][0].apply(n, arguments)
        }, {"../shared/constants": 328, "../shared/get-locale": 330, "braintree-utilities": 322, dup: 199}],
        327: [function (t, e, n) {
            arguments[4][200][0].apply(n, arguments)
        }, {"../shared/constants": 328, "braintree-utilities": 322, dup: 200}],
        328: [function (t, e, n) {
            arguments[4][201][0].apply(n, arguments)
        }, {dup: 201}],
        329: [function (t, e, n) {
            arguments[4][202][0].apply(n, arguments)
        }, {dup: 202}],
        330: [function (t, e, n) {
            arguments[4][203][0].apply(n, arguments)
        }, {"../shared/data/country-code-lookup": 329, dup: 203}],
        331: [function (t, e, n) {
            arguments[4][204][0].apply(n, arguments)
        }, {"./platform": 333, "./useragent": 334, dup: 204}],
        332: [function (t, e, n) {
            arguments[4][205][0].apply(n, arguments)
        }, {"./platform": 333, "./useragent": 334, dup: 205}],
        333: [function (t, e, n) {
            arguments[4][206][0].apply(n, arguments)
        }, {"./useragent": 334, dup: 206}],
        334: [function (t, e, n) {
            arguments[4][207][0].apply(n, arguments)
        }, {dup: 207}],
        335: [function (t, e, n) {
            arguments[4][208][0].apply(n, arguments)
        }, {
            "../useragent/browser": 331,
            "../useragent/device": 332,
            "../useragent/platform": 333,
            "../useragent/useragent": 334,
            dup: 208
        }],
        336: [function (t, e, n) {
            arguments[4][209][0].apply(n, arguments)
        }, {dup: 209}],
        337: [function (t, e, n) {
            arguments[4][210][0].apply(n, arguments)
        }, {dup: 210}],
        338: [function (t, e, n) {
            arguments[4][35][0].apply(n, arguments)
        }, {dup: 35}],
        339: [function (t, e, n) {
            arguments[4][22][0].apply(n, arguments)
        }, {dup: 22}],
        340: [function (t, e, n) {
            arguments[4][23][0].apply(n, arguments)
        }, {dup: 23}],
        341: [function (t, e, n) {
            arguments[4][24][0].apply(n, arguments)
        }, {dup: 24}],
        342: [function (t, e, n) {
            arguments[4][39][0].apply(n, arguments)
        }, {dup: 39}],
        343: [function (t, e, n) {
            arguments[4][40][0].apply(n, arguments)
        }, {"./array": 338, dup: 40}],
        344: [function (t, e, n) {
            arguments[4][41][0].apply(n, arguments)
        }, {
            "./lib/array": 338,
            "./lib/dom": 339,
            "./lib/events": 340,
            "./lib/fn": 341,
            "./lib/string": 342,
            "./lib/url": 343,
            dup: 41
        }],
        345: [function (t, e) {
            function n(t, e, n) {
                return "function" == typeof e ? r(t, !0, i(e, n, 1)) : r(t, !0)
            }

            var r = t("lodash._baseclone"), i = t("lodash._bindcallback");
            e.exports = n
        }, {"lodash._baseclone": 346, "lodash._bindcallback": 356}],
        346: [function (t, e) {
            (function (n) {
                function r(t, e, n, o, d, m, y) {
                    var b;
                    if (n && (b = d ? n(t, o, d) : n(t)), void 0 !== b)return b;
                    if (!c(t))return t;
                    var v = f(t);
                    if (v) {
                        if (b = s(t), !e)return l(t, b)
                    } else {
                        var _ = V.call(t), w = _ == E;
                        if (_ != A && _ != g && (!w || d))return B[_] ? u(t, _, e) : d ? t : {};
                        if (b = a(w ? {} : t), !e)return h(b, t)
                    }
                    m || (m = []), y || (y = []);
                    for (var C = m.length; C--;)if (m[C] == t)return y[C];
                    return m.push(t), y.push(b), (v ? p : i)(t, function (i, o) {
                        b[o] = r(i, e, n, o, t, m, y)
                    }), b
                }

                function i(t, e) {
                    return d(t, e, m)
                }

                function o(t) {
                    var e = new Y(t.byteLength), n = new q(e);
                    return n.set(new q(t)), e
                }

                function s(t) {
                    var e = t.length, n = new t.constructor(e);
                    return e && "string" == typeof t[0] && H.call(t, "index") && (n.index = t.index, n.input = t.input), n
                }

                function a(t) {
                    var e = t.constructor;
                    return "function" == typeof e && e instanceof e || (e = Object), new e
                }

                function u(t, e, n) {
                    var r = t.constructor;
                    switch (e) {
                        case P:
                            return o(t);
                        case b:
                        case v:
                            return new r(+t);
                        case N:
                        case I:
                        case R:
                        case k:
                        case D:
                        case M:
                        case U:
                        case L:
                        case F:
                            var i = t.buffer;
                            return new r(n ? o(i) : i, t.byteOffset, t.length);
                        case C:
                        case T:
                            return new r(t);
                        case S:
                            var s = new r(t.source, j.exec(t));
                            s.lastIndex = t.lastIndex
                    }
                    return s
                }

                function c(t) {
                    var e = typeof t;
                    return !!t && ("object" == e || "function" == e)
                }

                var l = t("lodash._arraycopy"), p = t("lodash._arrayeach"), h = t("lodash._baseassign"), d = t("lodash._basefor"), f = t("lodash.isarray"), m = t("lodash.keys"), g = "[object Arguments]", y = "[object Array]", b = "[object Boolean]", v = "[object Date]", _ = "[object Error]", E = "[object Function]", w = "[object Map]", C = "[object Number]", A = "[object Object]", S = "[object RegExp]", x = "[object Set]", T = "[object String]", O = "[object WeakMap]", P = "[object ArrayBuffer]", N = "[object Float32Array]", I = "[object Float64Array]", R = "[object Int8Array]", k = "[object Int16Array]", D = "[object Int32Array]", M = "[object Uint8Array]", U = "[object Uint8ClampedArray]", L = "[object Uint16Array]", F = "[object Uint32Array]", j = /\w*$/, B = {};
                B[g] = B[y] = B[P] = B[b] = B[v] = B[N] = B[I] = B[R] = B[k] = B[D] = B[C] = B[A] = B[S] = B[T] = B[M] = B[U] = B[L] = B[F] = !0, B[_] = B[E] = B[w] = B[x] = B[O] = !1;
                var z = Object.prototype, H = z.hasOwnProperty, V = z.toString, Y = n.ArrayBuffer, q = n.Uint8Array;
                e.exports = r
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {
            "lodash._arraycopy": 347,
            "lodash._arrayeach": 348,
            "lodash._baseassign": 349,
            "lodash._basefor": 351,
            "lodash.isarray": 352,
            "lodash.keys": 353
        }],
        347: [function (t, e) {
            function n(t, e) {
                var n = -1, r = t.length;
                for (e || (e = Array(r)); ++n < r;)e[n] = t[n];
                return e
            }

            e.exports = n
        }, {}],
        348: [function (t, e) {
            function n(t, e) {
                for (var n = -1, r = t.length; ++n < r && e(t[n], n, t) !== !1;);
                return t
            }

            e.exports = n
        }, {}],
        349: [function (t, e, n) {
            arguments[4][77][0].apply(n, arguments)
        }, {dup: 77, "lodash._basecopy": 350, "lodash.keys": 353}],
        350: [function (t, e, n) {
            arguments[4][78][0].apply(n, arguments)
        }, {dup: 78}],
        351: [function (t, e) {
            function n(t) {
                return function (e, n, i) {
                    for (var o = r(e), s = i(e), a = s.length, u = t ? a : -1; t ? u-- : ++u < a;) {
                        var c = s[u];
                        if (n(o[c], c, o) === !1)break
                    }
                    return e
                }
            }

            function r(t) {
                return i(t) ? t : Object(t)
            }

            function i(t) {
                var e = typeof t;
                return !!t && ("object" == e || "function" == e)
            }

            var o = n();
            e.exports = o
        }, {}],
        352: [function (t, e, n) {
            arguments[4][44][0].apply(n, arguments)
        }, {dup: 44}],
        353: [function (t, e, n) {
            arguments[4][46][0].apply(n, arguments)
        }, {dup: 46, "lodash._getnative": 354, "lodash.isarguments": 355, "lodash.isarray": 352}],
        354: [function (t, e, n) {
            arguments[4][47][0].apply(n, arguments)
        }, {dup: 47}],
        355: [function (t, e, n) {
            arguments[4][43][0].apply(n, arguments)
        }, {dup: 43}],
        356: [function (t, e, n) {
            arguments[4][80][0].apply(n, arguments)
        }, {dup: 80}],
        357: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n = {};
                if (t) {
                    for (e in t)t.hasOwnProperty(e) && (n[r(e)] = t[e]);
                    return n
                }
            }

            function r(t) {
                return t.replace(/([A-Z])/g, function (t) {
                    return "_" + t.toLowerCase()
                })
            }

            e.exports = {convertToLegacyShippingAddress: n}
        }, {}],
        358: [function (t, e) {
            "use strict";
            e.exports = {
                ROOT_SUCCESS_CALLBACK: "onPaymentMethodReceived",
                ROOT_ERROR_CALLBACK: "onError",
                ROOT_READY_CALLBACK: "onReady"
            }
        }, {}],
        359: [function (t, e) {
            "use strict";
            function n(t) {
                var e = s(t.merchantConfiguration);
                return o.on(o.events.PAYMENT_METHOD_GENERATED, function (t) {
                    o.emit(o.events.PAYMENT_METHOD_RECEIVED, t)
                }), e.configuration = s(t.gatewayConfiguration), e.coinbase = s(e.coinbase || {}), e.apiClient = new r.Client({
                    enableCORS: t.merchantConfiguration.enableCORS,
                    clientToken: t.gatewayConfiguration,
                    integration: "coinbase"
                }), i.create(e)
            }

            var r = t("braintree-api"), i = t("braintree-coinbase"), o = t("braintree-bus"), s = t("lodash.clonedeep");
            e.exports = {initialize: n}
        }, {"braintree-api": 21, "braintree-bus": 50, "braintree-coinbase": 53, "lodash.clonedeep": 345}],
        360: [function (t, e) {
            "use strict";
            function n(t) {
                var e, n = {clientToken: t.gatewayConfiguration, integration: t.integrationType};
                t.merchantConfiguration.enableCORS && (n.enableCORS = !0), e = new a.Client(n), r(t, e), i(t), o(t, e)
            }

            function r(t, e) {
                var n, r = t.merchantConfiguration, i = r[h.ROOT_SUCCESS_CALLBACK];
                r.id ? (n = u.setup(e, r), p.isFunction(i) && (n.onNonceReceived = function (t, e) {
                    t ? d.emit(d.events.ERROR, t) : i(e)
                })) : d.on(d.events.PAYMENT_METHOD_GENERATED, function (t) {
                    d.emit(d.events.PAYMENT_METHOD_RECEIVED, t)
                })
            }

            function i(t) {
                var e, n, r, i, o = t.merchantConfiguration, a = o.paypal;
                a && (e = s(o, "paypal"), n = e("onSuccess"), r = e("onCancelled"), a.paymentMethodNonceInputField || (i = document.createElement("input"), i.id = "braintree-custom-integration-dummy-input", a.paymentMethodNonceInputField = i), a.onSuccess = function (t) {
                    d.emit(d.events.PAYMENT_METHOD_GENERATED, t), n.apply(null, [t.nonce, t.details.email, f(t.details.shippingAddress)])
                }, a.onCancelled = function () {
                    d.emit(d.events.PAYMENT_METHOD_CANCELLED), r()
                }, o.enableCORS && (a.enableCORS = !0), c.create(t.gatewayConfiguration, a))
            }

            function o(t, e) {
                var n = t.merchantConfiguration;
                n.coinbase && (n.configuration = t.gatewayConfiguration, n.apiClient = e, delete n.paypal, l.create(n))
            }

            function s(t, e) {
                return function (n) {
                    return e in t && p.isFunction(t[e][n]) ? t[e][n] : function () {
                    }
                }
            }

            var a = t("braintree-api"), u = t("braintree-form"), c = t("braintree-paypal"), l = t("braintree-coinbase"), p = t("braintree-utilities"), h = t("../constants"), d = t("braintree-bus"), f = t("../compatibility").convertToLegacyShippingAddress;
            e.exports = {initialize: n}
        }, {
            "../compatibility": 357,
            "../constants": 358,
            "braintree-api": 21,
            "braintree-bus": 50,
            "braintree-coinbase": 53,
            "braintree-form": 256,
            "braintree-paypal": 324,
            "braintree-utilities": 344
        }],
        361: [function (t, e) {
            "use strict";
            function n(t) {
                return s.isFunction(t.paymentMethodNonceReceived) ? t.paymentMethodNonceReceived : null
            }

            function r(t) {
                return s.isFunction(t[u.ROOT_SUCCESS_CALLBACK])
            }

            function i(t) {
                var e = t.merchantConfiguration, i = n(e), s = r(e);
                return (i || s) && (e.paymentMethodNonceReceived = function (t) {
                    i && i.apply(null, [t.originalEvent, t.nonce]), delete t.originalEvent, a.emit(a.events.PAYMENT_METHOD_RECEIVED, c(t))
                }), o.create(t)
            }

            var o = t("braintree-dropin"), s = t("braintree-utilities"), a = t("braintree-bus"), u = t("../constants"), c = t("../lib/sanitize-payload");
            e.exports = {initialize: i}
        }, {
            "../constants": 358,
            "../lib/sanitize-payload": 366,
            "braintree-bus": 50,
            "braintree-dropin": 246,
            "braintree-utilities": 344
        }],
        362: [function (t, e) {
            "use strict";
            e.exports = {custom: t("./custom"), dropin: t("./dropin"), paypal: t("./paypal"), coinbase: t("./coinbase")}
        }, {"./coinbase": 359, "./custom": 360, "./dropin": 361, "./paypal": 363}],
        363: [function (t, e) {
            "use strict";
            function n(t) {
                return "onSuccess"in t && s.isFunction(t.onSuccess) ? t.onSuccess : "paypal"in t && s.isFunction(t.paypal.onSuccess) ? t.paypal.onSuccess : null
            }

            function r(t) {
                return s.isFunction(t[a.ROOT_SUCCESS_CALLBACK])
            }

            function i(t) {
                var e = t.merchantConfiguration, i = n(e), s = r(e);
                return (i || s) && (e.onSuccess = function (t) {
                    i && i.apply(null, [t.nonce, t.details.email, c(t.details.shippingAddress)]), u.emit(u.events.PAYMENT_METHOD_RECEIVED, t)
                }), o.create(t.gatewayConfiguration, e)
            }

            var o = t("braintree-paypal"), s = t("braintree-utilities"), a = t("../constants"), u = t("braintree-bus"), c = t("../compatibility").convertToLegacyShippingAddress;
            e.exports = {initialize: i}
        }, {
            "../compatibility": 357,
            "../constants": 358,
            "braintree-bus": 50,
            "braintree-paypal": 324,
            "braintree-utilities": 344
        }],
        364: [function (t, e) {
            "use strict";
            function n(t) {
                var e = "web." + t.integrationType + ".", n = i.configure({
                    enableCORS: t.merchantConfiguration.enableCORS,
                    clientToken: t.gatewayConfiguration,
                    analyticsConfiguration: t.analyticsConfiguration,
                    integration: t.integrationType
                }), s = r(e, n);
                o.on(o.events.SEND_ANALYTICS_EVENTS, s)
            }

            function r(t, e) {
                return function (n, r) {
                    var i;
                    for (n instanceof Array || (n = [n]), i = 0; i < n.length; i++)n[i] = t + n[i];
                    e.sendAnalyticsEvents(n, r)
                }
            }

            var i = t("braintree-api"), o = t("braintree-bus");
            e.exports = {listenForAnalytics: n, _createSender: r}
        }, {"braintree-api": 21, "braintree-bus": 50}],
        365: [function (t, e) {
            "use strict";
            function n(t) {
                this.callback = t, this.counter = 0, this.attachEvents()
            }

            var r = t("braintree-bus"), i = t("braintree-utilities");
            n.prototype.attachEvents = function () {
                this.initHandler = i.bind(this.handleDependencyInitializing, this), this.readyHandler = i.bind(this.handleDependencyReady, this), r.on(r.events.ASYNC_DEPENDENCY_INITIALIZING, this.initHandler), r.on(r.events.ASYNC_DEPENDENCY_READY, this.readyHandler)
            }, n.prototype.handleDependencyInitializing = function () {
                this.counter++
            }, n.prototype.handleDependencyReady = function () {
                this.counter--, 0 === this.counter && (this.detachEvents(), this.callback())
            }, n.prototype.detachEvents = function () {
                r.off(r.events.ASYNC_DEPENDENCY_INITIALIZING, this.initHandler), r.off(r.events.ASYNC_DEPENDENCY_READY, this.readyHandler)
            }, e.exports = function (t) {
                return new n(t)
            }
        }, {"braintree-bus": 50, "braintree-utilities": 344}],
        366: [function (t, e) {
            "use strict";
            e.exports = function (t) {
                return {nonce: t.nonce, details: t.details, type: t.type}
            }
        }, {}]
    }, {}, [1])(1)
});